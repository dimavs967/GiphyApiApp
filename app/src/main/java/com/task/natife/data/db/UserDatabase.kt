package com.task.natife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.natife.model.GifEntity

@Database(entities = [GifEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}