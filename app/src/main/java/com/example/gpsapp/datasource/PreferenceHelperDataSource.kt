package com.example.gpsapp.datasource

/**
 * This interface is used to show the call back methods for shared preference class.
 */
interface PreferenceHelperDataSource {
    fun setEmail(emailId: String)

    fun getEmail(): String?

    fun setAppSpotData(appSpotData: String)

    fun getAppSpotData(): String?

}