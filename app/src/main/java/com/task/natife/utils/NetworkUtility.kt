package com.task.natife.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkUtility : ConnectivityManager.NetworkCallback() {

    private val networkState = MutableLiveData<Boolean>()
    private var isAvailable = false

    fun getNetworkStateLiveData(context: Context): LiveData<Boolean> {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        isAvailable = manager.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            } else {
                registerNetworkCallback(NetworkRequest.Builder().build(), this@NetworkUtility)
                false
            }
        }

        networkState.postValue(isAvailable)
        return networkState
    }

    override fun onLost(@NonNull network: Network) {
        networkState.postValue(false)
    }

    override fun onAvailable(@NonNull network: Network) {
        networkState.postValue(true)
    }

}