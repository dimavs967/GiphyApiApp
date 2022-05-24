package com.task.natife.data.repository

import com.task.natife.data.db.GifDao
import com.task.natife.model.GifModel
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val gifDao: GifDao
) : IMainRepository {

    override suspend fun insertItem(gifModel: GifModel) {
        gifDao.insertItem(gifModel)
    }

    override fun getHiddenItems(): MutableList<GifModel> {
        return gifDao.getAllItems()
    }

}