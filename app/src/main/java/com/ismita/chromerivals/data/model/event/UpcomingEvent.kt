package com.ismita.chromerivals.data.model.event

data class UpcomingEvent (
    var deployTime: String? = null,
    var ani: String? = null,
    var bcu: String? = null,
    var outpostName: String? = null,
    var influence: Double? = null
): Event()
