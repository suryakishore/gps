package com.example.gpsapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gpsapp.databinding.ActivityMainBinding
import com.example.gpsapp.datasource.PreferencesHelper
import com.example.gpsapp.response.AppSpotItems

/**
 * This activity is used to show the app spot items in the recyclerview.if the data is available in the cache we are taking from the cache otherwise
 * we are calling an api to update the  shared preference.
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private var mAppSpotData = ArrayList<AppSpotItems>()
    lateinit var adapter: AppSpotAdapter
    lateinit var preferenceManager: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        subscribeAppSpotData()
    }

    /**
     *This method is used to initialize the dataBinding,view model, preference manger,retrofit and adapter class
     * and if the data is not there in the session manager we are calling an api or else we are taking the data from my cache and gain we are calling an api to update the preferences.
     */
    private fun initializeData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        preferenceManager = PreferencesHelper(this)
        binding.tvEmailId.setText(preferenceManager.getEmail())
        binding.viewModel = viewModel
        mAppSpotData.clear()
        adapter = AppSpotAdapter(mAppSpotData)
        binding.rvAppspot.adapter = adapter
        viewModel.initializeRetrofit(this)
        if (!preferenceManager.getAppSpotData()?.isEmpty()!!)
            viewModel.getAppSpotData()
        else
            viewModel.getAppspotResults(false, preferenceManager.getEmail())
    }

    /**
     * Subscribe to app spot data which is coming from server
     */
    private fun subscribeAppSpotData() {
        viewModel.onAppSpotData().observe(this, Observer {
            if (it != null) {
                binding.pgLoadData.visibility = View.GONE
                mAppSpotData.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}