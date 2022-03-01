package com.ismita.chromerivals.data.model.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class EventDB (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    var deployTime: String? = null,
    var ani: String? = null,
    var bcu: String? = null,
    var outpostName: String? = null,
    var influence: Double? = null
)
