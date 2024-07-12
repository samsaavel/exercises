package com.greenv.feb14.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenv.feb14.databinding.ItemRowListBinding
import com.greenv.feb14.response.ResultObject

class RamAdapter(
    private val ramStructure: List<ResultObject>,
) : RecyclerView.Adapter<RamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RamViewHolder {
        val binding = ItemRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RamViewHolder, position: Int) {
        holder.bindModelToView(ramStructure[position])
    }

    override fun getItemCount() = ramStructure.size

//    @SuppressLint("NotifyDataSetChanged")
//    fun addRamList(newRamList: List<ResultObject>) {
//        ramStructure.addAll(newRamList)
//        notifyDataSetChanged()
//    }
}