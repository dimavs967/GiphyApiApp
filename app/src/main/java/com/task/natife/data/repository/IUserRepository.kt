package com.task.natife.data.repository

import com.task.natife.model.GifModel

interface IUserRepository {

    suspend fun insertItem(gifModel: GifModel)

    fun getItem(id: String): GifModel

}