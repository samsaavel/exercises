package com.greenv.feb14.ui.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenv.feb14.response.ResultObject

class RamAdapter : RecyclerView.Adapter<RamViewHolder>() {

    private val ramStructure = mutableListOf<ResultObject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RamViewHolder {
        return RamViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RamViewHolder, position: Int) {
        holder.bindModelToView(ramStructure[position])
    }

    override fun getItemCount() = ramStructure.size

    @SuppressLint("NotifyDataSetChanged")
    fun addRamList(newRamList: List<ResultObject>) {
        ramStructure.addAll(newRamList)
        notifyDataSetChanged()
    }
}