package com.task.natife.data.repository

import com.task.natife.model.GifListModel
import com.task.natife.model.GifModel

interface IMainRepository {

    suspend fun insertItem(gifModel: GifModel)

    fun getHiddenItems(): MutableList<GifModel>

    suspend fun insertList(list: GifListModel)

    fun getAllList(): List<GifListModel>

}