package com.task.natife.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.task.natife.data.remote.VolleyService
import com.task.natife.data.repository.UserRepository
import com.task.natife.model.GifModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class MainViewModel @Inject constructor(
    private val netService: VolleyService,
    private val repo: UserRepository
) : ViewModel() {

    private val listLiveData = MutableLiveData<MutableList<GifModel>>()

    fun getListLiveData(): LiveData<MutableList<GifModel>> {
        return listLiveData
    }

    suspend fun searchRequest(item: String) {
        val gifsList = parseJson(
            netService.jsonRequest(item)
        )

        listLiveData.postValue(gifsList)
    }

    suspend fun insertItem(i: Int) {
        listLiveData.value?.get(i)?.let { repo.insertItem(it) }
        listLiveData.value?.removeAt(i)
        listLiveData.notifyObserver()
    }

    private suspend fun parseJson(response: JSONObject): MutableList<GifModel> {
        return suspendCoroutine {

            val list = mutableListOf<GifModel>()
            val data = Gson().fromJson(
                response.toString(),
                JsonObject::class.java
            ).getAsJsonArray("data")

            for (i in 0 until data.size()) {
                list.add(
                    GifModel(
                        data.get(i).asJsonObject["type"].asString,
                        data.get(i).asJsonObject["id"].asString,
                        data.get(i).asJsonObject["images"]
                            .asJsonObject["downsized_medium"]
                            .asJsonObject["url"]
                            .asString
                    )
                )
            }

            it.resume(list)
        }
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}