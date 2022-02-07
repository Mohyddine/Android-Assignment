package com.mehyo.androidassignment.ui.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehyo.androidassignment.model.LaunchItem
import com.mehyo.androidassignment.databinding.LaunchRowBinding

class LaunchesAdapter(private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<LaunchesViewHolder>() {

    private var launchesItems= listOf<LaunchItem>()

    fun setData(list:List<LaunchItem>){
        this.launchesItems=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder = LaunchesViewHolder(
        LaunchRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) = holder.bind(launchesItems[position],itemClickListener)

    override fun getItemCount(): Int = launchesItems.size
}