package com.example.gpsapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AppSpotResponse {

    @Expose
    @SerializedName("items")
     val items: ArrayList<AppSpotItems>? = null

}