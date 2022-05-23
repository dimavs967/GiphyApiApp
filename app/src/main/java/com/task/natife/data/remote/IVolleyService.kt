package com.task.natife.data.remote

import org.json.JSONArray

interface IVolleyService {
    suspend fun jsonRequest(item: String): JSONArray
}