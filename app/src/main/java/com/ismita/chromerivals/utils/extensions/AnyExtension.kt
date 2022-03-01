package com.ismita.chromerivals.utils.extensions

import com.google.gson.Gson
import com.ismita.chromerivals.data.model.event.CurrentEvent
import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.data.model.pedia.Item
import com.ismita.chromerivals.data.model.pedia.Monster

object AnyExtension {

    fun Any.toUpcomingEvent(): UpcomingEvent {
        return Gson().fromJson(Gson().toJsonTree(this), UpcomingEvent::class.java)
    }

    fun Any.toCurrentEvent(): CurrentEvent {
        return Gson().fromJson(Gson().toJsonTree(this), CurrentEvent::class.java)
    }

    fun Any.toItem(): Item {
        return Gson().fromJson(Gson().toJsonTree(this), Item::class.java)
    }

    fun Any.toMonster(): Monster {
        return Gson().fromJson(Gson().toJsonTree(this), Monster::class.java)
    }

    fun Any.toUpcomingEventList(): List<UpcomingEvent> {
        return Gson().fromJson(Gson().toJsonTree(this), Array<UpcomingEvent>::class.java).toMutableList()
    }

    fun Any.toEventDBList(): List<EventDB> {
        return Gson().fromJson(Gson().toJsonTree(this), Array<EventDB>::class.java).toMutableList()
    }

}