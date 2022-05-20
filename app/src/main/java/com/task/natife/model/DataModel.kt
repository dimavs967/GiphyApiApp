package com.task.natife.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.task.natife.utils.ListConverter

@Entity(tableName = "gifs_table")
data class GifModel(
    var type: String,
    var gifId: String,
    var gifUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}



//@Entity(tableName = "list_table")
//data class GifListModel(
//    var list: GifsModel
//) {
//    @PrimaryKey(autoGenerate = true)
//    var id: Int? = null
//}
//
//data class GifsModel(
//    @TypeConverters(ListConverter::class)
//    var list: List<GifModel>
//)