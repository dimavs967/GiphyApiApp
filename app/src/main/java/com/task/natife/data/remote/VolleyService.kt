package com.task.natife.data.remote

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.task.natife.constants.Constants.BASE_URL
import com.task.natife.constants.Constants.GIPHY_API_KEY
import org.json.JSONArray
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class VolleyService @Inject constructor(
    private val requestQueue: RequestQueue
) {

    suspend fun jsonRequest(item: String): JSONArray {
        val url = "${BASE_URL}search?api_key=${GIPHY_API_KEY}&amp;q=${item}&amp;limit=10"

        return suspendCoroutine {
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val dataArray = response.getJSONArray("data")

                    it.resume(dataArray)
                },
                { error ->
                    Log.e("VolleyError", "Error: $error")
                }
            )

            requestQueue.add(jsonObjectRequest)
        }
    }

//    suspend fun jsonRequest(item: String): MutableList<GifEntity> {
//
//        val url = "${BASE_URL}search?api_key=${GIPHY_API_KEY}&amp;q=${item}&amp;limit=20"
//        val list = mutableListOf<GifEntity>()
//
//        return suspendCoroutine {
//            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//                { response ->
//                    val dataArray = response.getJSONArray("data")
//
//                    for (i in 0 until dataArray.length()) {
//                        val imagesField = dataArray.getJSONObject(i).get("images") as JSONObject
//                        val sizeField = imagesField.getJSONObject("downsized_medium")
//
//                        list.add(
//                            GifEntity(
//                                i,
//                                dataArray.getJSONObject(i).getString("type"),
//                                dataArray.getJSONObject(i).getString("id"),
//                                sizeField.getString("url")
//                            )
//                        )
//                    }
//
//                    it.resume(list)
//                },
//                { error ->
//                    Log.e("VolleyError", "Error: $error")
//                }
//            )
//
//            requestQueue.add(jsonObjectRequest)
//        }
//    }
}