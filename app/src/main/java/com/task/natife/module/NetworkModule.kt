package com.task.natife.module

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.task.natife.data.remote.VolleyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideVolleyService(
        requestQueue: RequestQueue
    ): VolleyService {
        return VolleyService(requestQueue)
    }

    @Singleton
    @Provides
    fun provideRequestQueue(
        @ApplicationContext context: Context
    ): RequestQueue {
        return Volley.newRequestQueue(context)
    }

}