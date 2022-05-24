package com.task.natife.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gifs_table")
data class GifModel(
    var type: String,
    var gifId: String,
    var gifUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "gifs_list_table")
data class GifListModel(
    var list: List<GifModel>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}