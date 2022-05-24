package com.task.natife.data.repository

import com.task.natife.data.db.GifDao
import com.task.natife.data.db.GifListDao
import com.task.natife.model.GifListModel
import com.task.natife.model.GifModel
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val gifDao: GifDao,
    private val gifListDao: GifListDao
) : IMainRepository {

    override suspend fun insertItem(gifModel: GifModel) {
        gifDao.insertItem(gifModel)
    }

    override fun getHiddenItems(): MutableList<GifModel> {
        return gifDao.getAllItems()
    }

    override suspend fun insertList(list: GifListModel) {
        gifListDao.insertList(list)
    }

    override fun getAllList(): List<GifListModel> {
        return gifListDao.getAllList()
    }

}