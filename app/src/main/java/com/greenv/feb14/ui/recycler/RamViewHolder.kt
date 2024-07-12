package com.greenv.feb14.ui.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greenv.feb14.databinding.ItemRowListBinding
import com.greenv.feb14.response.ResultObject

class RamViewHolder(
    private val binding: ItemRowListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindModelToView(model: ResultObject) {
        Log.d("****VHolder", "binding character ")
        binding.ramName.text = model.name
        binding.ramSpecie.text = model.species
        Glide.with(binding.ramImage.context).load(model.image).into(binding.ramImage)
    }
}