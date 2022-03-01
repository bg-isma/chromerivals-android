package com.ismita.chromerivals.presenntation.ui.pedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismita.chromerivals.data.model.pedia.Item
import com.ismita.chromerivals.data.model.pedia.Monster
import com.ismita.chromerivals.data.model.pedia.PediaElement
import com.ismita.chromerivals.domain.pedia.GetSearchedItemInfoUseCase
import com.ismita.chromerivals.domain.pedia.GetSearchedMonsterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getSearchedItemInfoUseCase: GetSearchedItemInfoUseCase,
    private val getSearchedMonsterInfoUseCase: GetSearchedMonsterInfoUseCase
): ViewModel() {

    val pediaElement = MutableLiveData<PediaElement>()

    fun getElementInfo(newPediaElement: PediaElement) {
        viewModelScope.launch {
             when (newPediaElement) {
                is Monster -> {
                    val id = newPediaElement.monsterCode?.idString ?: ""
                    val monster = getSearchedMonsterInfoUseCase(id)
                    pediaElement.postValue(monster)
                }
                is Item -> {
                    val id = newPediaElement.itemCode?.idString ?: ""
                    val item = getSearchedItemInfoUseCase(id)
                    pediaElement.postValue(item)
                }
            }
        }
    }


}