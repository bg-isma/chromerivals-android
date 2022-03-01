package com.ismita.chromerivals.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismita.chromerivals.models.pedia.*
import com.ismita.chromerivals.models.pedia.typesEnum.CategoryType
import com.ismita.chromerivals.services.domain.history.DeleteAllHistoryElementsUseCase
import com.ismita.chromerivals.services.domain.history.DeleteHistoryElementUseCase
import com.ismita.chromerivals.services.domain.history.GetAllHistoryElementsUseCase
import com.ismita.chromerivals.services.domain.history.InsertHistoryElementUseCase
import com.ismita.chromerivals.services.domain.pedia.GetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultsUseCase,
    private val getAllHistoryElementsUseCase: GetAllHistoryElementsUseCase,
    private val insertHistoryElementUseCase: InsertHistoryElementUseCase,
    private val deleteAllHistoryElementsUseCase : DeleteAllHistoryElementsUseCase,
    private val deleteHistoryElementUseCase: DeleteHistoryElementUseCase
): ViewModel() {

    val searchResult = MutableLiveData<List<PediaElement>>()
    private var noFilteredPediaElements = emptyList<PediaElement>()

    val historyElements = MutableLiveData<List<HistoryElementDB>>()

    fun onCreate() {
        viewModelScope.launch {
            searchResult.postValue(listOf())
            historyElements.postValue(getAllHistoryElementsUseCase())
        }
    }

    fun searchNewResults(query: String) {
        viewModelScope.launch {
            val result = getSearchResultUseCase(query)
            noFilteredPediaElements = result
            searchResult.postValue(result)
        }
    }

    fun filterByCategoryType(categoryType: CategoryType) {
        when(categoryType) {
            CategoryType.Item -> searchResult.postValue(noFilteredPediaElements.filterIsInstance<Item>())
            CategoryType.Monster -> searchResult.postValue(noFilteredPediaElements.filterIsInstance<Monster>())
            else -> searchResult.postValue(noFilteredPediaElements)
        }
    }

    fun deleteHistoryElementAtIndex(item: HistoryElementDB) {
        viewModelScope.launch {
            deleteHistoryElementUseCase(item)
            historyElements.postValue(getAllHistoryElementsUseCase())
        }
    }

    fun addElementToHistory(query: String) {
        viewModelScope.launch {
            insertHistoryElementUseCase(query)
            historyElements.postValue(getAllHistoryElementsUseCase())
        }
    }

    fun deleteAllHistory() {
        viewModelScope.launch {
            deleteAllHistoryElementsUseCase()
            historyElements.postValue(getAllHistoryElementsUseCase())
        }
    }

}