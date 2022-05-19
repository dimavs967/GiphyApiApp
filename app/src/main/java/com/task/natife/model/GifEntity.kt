package com.task.natife.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gifs_table")
data class GifEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var type: String,
    var url: String
)