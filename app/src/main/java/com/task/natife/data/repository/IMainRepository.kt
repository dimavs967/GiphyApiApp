package com.task.natife.data.repository

import com.task.natife.model.GifModel

interface IMainRepository {

    suspend fun insertItem(gifModel: GifModel)

    fun getHiddenItems(): MutableList<GifModel>

}