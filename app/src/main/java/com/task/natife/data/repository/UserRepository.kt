package com.task.natife.data.repository

import com.task.natife.data.db.GifDao
import com.task.natife.model.GifModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val gifDao: GifDao
): IUserRepository {

    override suspend fun insertItem(gifModel: GifModel) {
        gifDao.insertItem(gifModel)
    }

    override fun getAllItems(): MutableList<GifModel> {
        return gifDao.getAllItems()
    }

}