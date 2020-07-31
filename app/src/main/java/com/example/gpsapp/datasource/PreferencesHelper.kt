package com.example.gpsapp.datasource

import android.content.Context
import android.content.SharedPreferences
import com.example.Constants.APP_SPOT_DATA
import com.example.Constants.EMAIL_ID

/**
 * This class is used to store the data locally which is shared preference
 */
class PreferencesHelper : PreferenceHelperDataSource {
    private val PREF_NAME = "AppSpot"
    var editor: SharedPreferences.Editor
    var sharedPreferences: SharedPreferences

    constructor(context: Context) {
        val PRIVATE_MODE = 0
        sharedPreferences = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        editor = sharedPreferences!!.edit()
        editor.apply()
    }

    override fun setEmail(emailId: String) {
        editor.putString(EMAIL_ID, emailId)
        editor.commit()
    }

    override fun getEmail(): String? {
        return sharedPreferences.getString(EMAIL_ID, "")
    }

    override fun setAppSpotData(appSpotData: String) {
        editor.putString(APP_SPOT_DATA, appSpotData)
        editor.commit()
    }

    override fun getAppSpotData(): String? {
        return sharedPreferences.getString(APP_SPOT_DATA, "")
    }
}