package com.task.natife.data.repository

import com.task.natife.data.db.GifDao
import com.task.natife.data.db.GifListDao
import com.task.natife.model.GifListModel
import com.task.natife.model.GifModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val gifDao: GifDao,
    private val gifListDao: GifListDao
) {

    suspend fun insertList(list: GifListModel) {
        gifListDao.insertList(list)
    }

    suspend fun getList(): GifListModel {
        return gifListDao.getAllItems()
    }

}