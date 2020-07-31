package com.example.gpsapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Constants.SUCCESS
import com.example.gpsapp.datasource.PreferencesHelper
import com.example.gpsapp.response.AppSpotItems
import com.example.gpsapp.response.AppSpotResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * view model class for the main activity.
 */
class MainViewModel() : ViewModel() {

    private var mSearchData = MutableLiveData<ArrayList<AppSpotItems>>()
    private var networkService: NetworkService? = null
    lateinit var preferenceManager: PreferencesHelper

    fun initializeRetrofit(context: Context) {

        networkService = NetworkManager.initializeBaseUrlRetrofit(context)
        preferenceManager = PreferencesHelper(context)

    }

    /**
     * This method used to get the appspot result when we call the api using emailId Data.
     */
    fun getAppspotResults(isToUpdate: Boolean, emailId: String?) {
        val appSpotRequest = AppSpotRequest()
        appSpotRequest.emailId = emailId
        networkService.also { it ->
            it!!.getAppspotResults(
                appSpotRequest
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (it != null) {
                        try {
                            val jsonObject: JSONObject
                            val code = it.code()
                            Log.d("exe", "code" + code)
                            if (code == SUCCESS) {
                                val response: String = it.body()!!.string()
                                Log.d("exe", "response" + response)
                                jsonObject = JSONObject(response)
                                val gson = Gson()
                                val appSpotResponse =
                                    gson.fromJson(
                                        jsonObject.toString(),
                                        AppSpotResponse::class.java
                                    )
                                if (appSpotResponse != null && appSpotResponse.items != null) {
                                    if (!isToUpdate)
                                        mSearchData.postValue(appSpotResponse.items)
                                    else
                                        preferenceManager.setAppSpotData(response)
                                }
                            } else {
                                jsonObject = JSONObject(it.errorBody()!!.string())
                                mSearchData.postValue(null)
                            }
                        } catch (e: JSONException) {
                            mSearchData.postValue(null)
                        } catch (e: IOException) {
                            mSearchData.postValue(null)
                        }
                    }
                }
        }
    }

    /**
     * This method used to get the app spot data from the session manager
     */
    fun getAppSpotData() {
        val gson = Gson()
        val appSpotResponse =
            gson.fromJson(
                preferenceManager.getAppSpotData(),
                AppSpotResponse::class.java
            )
        if (appSpotResponse != null && appSpotResponse.items != null) {
            mSearchData.postValue(appSpotResponse.items)
        }
        getAppspotResults(true, preferenceManager.getEmail())
    }

    /**
     * notify activity when app spot data comes
     */
    fun onAppSpotData(): MutableLiveData<ArrayList<AppSpotItems>> {
        return mSearchData
    }


}