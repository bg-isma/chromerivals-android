package com.ismita.chromerivals.models.event.typesEnum

import android.content.res.Resources
import com.ismita.chromerivals.R

enum class EventType(val typeName: String) {
    NATION_KILL_EVENT("Nation Kill Event"),
    FREE_FOR_ALL("Free For All"),
    OUTPOST("Outpost"),
    DEFAULT_EVENT_TYPE("Event")
}

/*
val RSystem: Resources = Resources.getSystem()
enum class EventType(val typeName: String) {
    NATION_KILL_EVENT(RSystem.getString(R.string.nation_kill_event)),
    FREE_FOR_ALL(RSystem.getString(R.string.free_for_all)),
    OUTPOST(RSystem.getString(R.string.outpost)),
    DEFAULT_EVENT_TYPE(RSystem.getString(R.string.default_event_name))
}
*/