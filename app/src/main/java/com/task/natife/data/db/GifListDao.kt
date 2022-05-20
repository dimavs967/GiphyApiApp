package com.task.natife.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.natife.model.GifListModel
import com.task.natife.model.GifModel

@Dao
interface GifListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(gifList: GifListModel)

    @Query("SELECT * FROM gifs_table")
    fun getAllItems(): GifListModel
}