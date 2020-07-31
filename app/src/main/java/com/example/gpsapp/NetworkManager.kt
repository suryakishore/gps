package com.example.gpsapp

import android.content.Context
import com.example.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * network manager class to interact with server.
 */
class NetworkManager {

    companion object {
        private val httpClient = OkHttpClient().newBuilder()

        /**
         * initialize retrofit for getting the app spot results from server
         */
        fun initializeBaseUrlRetrofit(context: Context): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            val service = retrofit.create(NetworkService::class.java)
            return service
        }
    }
}