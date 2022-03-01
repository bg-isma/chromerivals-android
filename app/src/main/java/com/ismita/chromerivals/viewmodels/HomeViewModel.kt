package com.ismita.chromerivals.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismita.chromerivals.models.event.Event
import com.ismita.chromerivals.models.ThemeDB
import com.ismita.chromerivals.models.event.UpcomingEvent
import com.ismita.chromerivals.models.pedia.typesEnum.CategoryType
import com.ismita.chromerivals.services.domain.events.GetCurrentEventsUseCase
import com.ismita.chromerivals.services.domain.events.GetMothershipsEventsUseCase
import com.ismita.chromerivals.services.domain.events.GetOutpostsEventsUseCase
import com.ismita.chromerivals.services.repositories.ChromeRivalsThemeRepository
import com.ismita.chromerivals.utils.Utils.concatenate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOutpostsEventsUseCase : GetOutpostsEventsUseCase,
    private val getMothershipsEventsUseCase: GetMothershipsEventsUseCase,
    private val getCurrentEventsUseCase: GetCurrentEventsUseCase,
    private val chromeRivalsThemeRepository: ChromeRivalsThemeRepository
): ViewModel() {

    val upComingEvents = MutableLiveData<List<Event>>()
    private var noFilteredUpcomingEvent = emptyList<UpcomingEvent>()
    val currentEvents = MutableLiveData<List<Event>>()
    val theme = MutableLiveData<ThemeDB>()

    fun getTheme() {
        viewModelScope.launch {
            theme.postValue(chromeRivalsThemeRepository.getTheme())
        }
    }

    fun updateTheme(theme: ThemeDB) {
        viewModelScope.launch {
            chromeRivalsThemeRepository.updateTheme(theme)
        }
    }

    fun getUpcomingEvents() {
        viewModelScope.launch {
            val outposts = getOutpostsEventsUseCase()
            val motherships = getMothershipsEventsUseCase()
            val events = concatenate(outposts, motherships)
            noFilteredUpcomingEvent = events
            upComingEvents.postValue(events)
        }
    }

    fun insertTheme(theme: ThemeDB) {
        viewModelScope.launch {
            chromeRivalsThemeRepository.insertTheme(theme)
        }
    }

    fun getCurrentEvents() {
        viewModelScope.launch {
            currentEvents.postValue(getCurrentEventsUseCase())
        }
    }

    fun deleteCurrentEventByIndex(index: Int) {
        currentEvents.value.let { events ->
            val eventsFiltered = events?.filterIndexed { position, _ ->
                position != index
            }
            currentEvents.postValue(eventsFiltered)
        }
    }

    fun filterByCategoryType(categoryType: CategoryType) {
        when(categoryType) {
            CategoryType.Outpost -> {
                val eventsFiltered = noFilteredUpcomingEvent.filter{ event -> event.outpostName != null }
                upComingEvents.postValue(eventsFiltered)
            }
            CategoryType.Mothership -> {
                val eventsFiltered = noFilteredUpcomingEvent.filter{ event -> event.ani != null || event.bcu != null }
                upComingEvents.postValue(eventsFiltered)
            }
            else -> upComingEvents.postValue(noFilteredUpcomingEvent)
        }
    }

}