package com.task.natife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.natife.model.GifModel
import com.task.natife.model.GifListModel
import com.task.natife.utils.ListConverter

@Database(entities = [GifModel::class, GifListModel::class], version = 2)
@TypeConverters(ListConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
    abstract fun listDao(): GifListDao
}