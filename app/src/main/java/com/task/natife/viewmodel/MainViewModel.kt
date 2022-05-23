package com.task.natife.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.natife.data.remote.VolleyService
import com.task.natife.data.repository.UserRepository
import com.task.natife.model.GifModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONArray
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
        val data = parseJson(netService.jsonRequest(item))
        listLiveData.postValue(data)
    }

    suspend fun insertItem(i: Int) {
        listLiveData.value?.get(i)?.let { repo.insertItem(it) }
        listLiveData.value?.removeAt(i)
        listLiveData.notifyObserver()
    }

    private suspend fun parseJson(dataArray: JSONArray): MutableList<GifModel> {
        return suspendCoroutine {

            val list = mutableListOf<GifModel>()

            for (i in 0 until dataArray.length()) {
                val imagesField = dataArray.getJSONObject(i).get("images") as JSONObject
                val sizeField = imagesField.getJSONObject("downsized_medium")

                list.add(
                    GifModel(
                        dataArray.getJSONObject(i).getString("type"),
                        dataArray.getJSONObject(i).getString("id"),
                        sizeField.getString("url")
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