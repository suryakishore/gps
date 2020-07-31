package com.example.gpsapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.gpsapp.MainActivity
import com.example.gpsapp.R
import com.example.gpsapp.databinding.ActivityLoginBinding
import com.example.gpsapp.datasource.PreferencesHelper
import com.example.gpsapp.util.Utility
import kotlinx.android.synthetic.main.activity_login.*

/**
 * This activity is used to show the edit text field to enter a email id from the user.
 */
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var preferenceManager: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
    }

    /**
     *This method is used to initialize the dataBinding and preference manger
     * and added the functionality for clicking on login button.
     */
    private fun initializeData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        preferenceManager = PreferencesHelper(this)
        binding.btLogin.setOnClickListener {
            if (!etEmailId.text.toString().isEmpty()) {
                if (Utility.isEmail(etEmailId.text.toString())) {
                    preferenceManager.setEmail(etEmailId.text.toString())
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext, resources.getString(R.string.enterValidEmailId),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext, resources.getString(R.string.enterEmailId),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}