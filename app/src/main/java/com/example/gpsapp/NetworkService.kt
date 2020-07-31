package com.example.gpsapp

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * This interface is used to call the app spot api.
 */
interface NetworkService {
    @POST("list")
    fun getAppspotResults(
        @Body request: AppSpotRequest
    ): Observable<Response<ResponseBody?>>


}