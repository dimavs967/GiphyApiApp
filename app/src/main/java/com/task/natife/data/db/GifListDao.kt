package com.task.natife.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.natife.model.GifListModel

@Dao
interface GifListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(gifModel: GifListModel)

    @Query("SELECT * FROM gifs_list_table")
    fun getAllList(): List<GifListModel>

}
