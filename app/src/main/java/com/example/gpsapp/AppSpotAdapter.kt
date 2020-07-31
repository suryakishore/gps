package com.example.gpsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gpsapp.databinding.ItemAppspotBinding
import com.example.gpsapp.response.AppSpotItems


/**
 * Adapter class for the app spot items.
 */
public class AppSpotAdapter(data: ArrayList<AppSpotItems>) :
    RecyclerView.Adapter<AppSpotAdapter.AppSpotViewHolder>() {
    private var mAppSpotData: ArrayList<AppSpotItems>
    lateinit var context: Context

    init {
        mAppSpotData = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppSpotAdapter.AppSpotViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemAppspotBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_appspot, parent, false)
        return AppSpotViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mAppSpotData != null) mAppSpotData.size else 0
    }

    override fun onBindViewHolder(holder: AppSpotAdapter.AppSpotViewHolder, position: Int) {
        val appspotOptions = mAppSpotData.get(position)
        if (appspotOptions != null) {
            Glide.with(context)
                .load(appspotOptions.imageUrl)
                .into(holder.itemBinding.ivAppSpotItem)
            holder.itemBinding.tvEmailId.text = appspotOptions.emailId
            holder.itemBinding.tvFirstName.text = appspotOptions.firstName
            holder.itemBinding.tvLastName.text = appspotOptions.lastName
        }
    }

    /**
     * view holder class for appspot items
     */
    class AppSpotViewHolder(itemBinding: ItemAppspotBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var itemBinding: ItemAppspotBinding

        init {
            this.itemBinding = itemBinding
        }
    }
}