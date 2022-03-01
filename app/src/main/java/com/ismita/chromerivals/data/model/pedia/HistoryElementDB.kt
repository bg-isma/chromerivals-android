package com.ismita.chromerivals.data.model.pedia

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryElementDB(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var query: String
)