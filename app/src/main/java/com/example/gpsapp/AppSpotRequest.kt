package com.example.gpsapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AppSpotRequest {

    @Expose
    @SerializedName("emailId")
    var emailId: String? = null


}