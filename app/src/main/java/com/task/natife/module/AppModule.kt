package com.task.natife.module

import android.content.Context
import androidx.room.Room
import com.task.natife.data.db.UserDatabase
import com.task.natife.data.repository.UserRepository
import com.task.natife.utils.ListConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext context: Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "gifs_table"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserRepository(database: UserDatabase): UserRepository {
        return UserRepository(database.gifDao(), database.listDao())
    }

}