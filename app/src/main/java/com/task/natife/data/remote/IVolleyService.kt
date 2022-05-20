package com.task.natife.data.remote

import com.task.natife.model.GifModel

interface IVolleyService {
    suspend fun jsonRequest(item: String): MutableList<GifModel>
}