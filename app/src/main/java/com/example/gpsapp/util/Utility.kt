package com.example.gpsapp.util

import android.util.Patterns

/**
 * This class is used to show the utility methods which are companion objects used by through out  the app
 */
class Utility {

    companion object {
        /*matches the email format*/
        fun isEmail(str: CharSequence?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(str).matches()
        }
    }
}