package com.greenv.feb14.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greenv.feb14.databinding.ItemRowListBinding
import com.greenv.feb14.response.ResultObject

class RamViewHolder(
    val parent: ViewGroup,
    private val binding: ItemRowListBinding = ItemRowListBinding.inflate(
        LayoutInflater.from(parent.context), parent, false),
) : RecyclerView.ViewHolder(binding.root) {

    fun bindModelToView(model: ResultObject) {
        binding.ramName.text = model.name
        binding.ramSpecie.text = model.species
        Glide.with(parent).load(model.image).into(binding.ramImage)
    }
}