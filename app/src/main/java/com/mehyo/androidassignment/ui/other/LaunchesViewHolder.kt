package com.mehyo.androidassignment.ui.other

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mehyo.androidassignment.databinding.LaunchRowBinding
import com.mehyo.androidassignment.model.LaunchItem

class LaunchesViewHolder(private val binding: LaunchRowBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(launchItem: LaunchItem,clickListener: OnItemClickListener){
        binding.apply {
            tvLaunchNumber.text=launchItem.flightNumber.toString()
            tvLaunchDate.text=launchItem.staticFireDateUtc
            tvLaunchName.text=launchItem.name
            tvLaunchDetails.text=launchItem.details
            launchesCard.setOnClickListener {
                clickListener.onItemClicked(launchItem.rocketID)
            }
            when (launchItem.upcoming) {
                true -> {
                    indicator.visibility= View.VISIBLE
                }
                null ->{
                    indicator.visibility= View.GONE
                }
                else -> {
                    indicator.visibility= View.GONE
                }
            }
        }
    }
}