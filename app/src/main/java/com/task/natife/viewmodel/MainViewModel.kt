package com.task.natife.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.task.natife.constants.Constants.BASE_URL
import com.task.natife.constants.Constants.GIPHY_API_KEY
import com.task.natife.data.remote.RetrofitService
import com.task.natife.model.GifEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {



}