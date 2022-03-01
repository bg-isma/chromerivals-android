package com.ismita.chromerivals.models.event

import com.ismita.chromerivals.R
import com.ismita.chromerivals.models.event.typesEnum.EventType

data class CurrentEvent (
    var mapsName: String? = null,
    var influenceType: Double? = null,
    var eventType: Double? = null,
    var maps: List<Int>? = null,
    var startTime: String? = null,
    var endTime: String? = null,
    var mapName: String? = null
) : Event() {

    fun getTypeName(): String {
        return when (eventType?.toInt()) {
            27 -> EventType.NATION_KILL_EVENT.typeName
            28 -> EventType.FREE_FOR_ALL.typeName
            3 ->  EventType.OUTPOST.typeName
            else -> EventType.DEFAULT_EVENT_TYPE.typeName
        }
    }
}
