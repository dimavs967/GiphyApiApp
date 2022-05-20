package com.task.natife.data.repository

import com.task.natife.model.GifModel

interface IUserRepository {

    suspend fun insertItem(gifModel: GifModel)

    suspend fun insertList(gifList: List<GifModel>)

    suspend fun getList(): List<GifModel>

}