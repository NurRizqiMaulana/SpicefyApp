package com.dicoding.spicefyapp.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.spicefyapp.databinding.ItemRowSpicelibBinding
import com.dicoding.spicefyapp.data.model.SpiceModel

class SpicelibListAdapter(private var listSpiceLib: List<SpiceModel>) : RecyclerView.Adapter<SpicelibListAdapter.MyViewHolder>() {
    class MyViewHolder(binding: ItemRowSpicelibBinding) : RecyclerView.ViewHolder(binding.root) {

        val imgSpice = binding.ivSpice
        val tvSpice = binding.tvNameSpice
        fun bind (spice : SpiceModel){
            imgSpice.setImageResource(spice.picture)
            tvSpice.text = spice.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowSpicelibBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listSpiceLib.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listSpiceLib[position])
    }

    fun setFilteredList(listSpiceLib: List<SpiceModel>){
        this.listSpiceLib = listSpiceLib
        notifyDataSetChanged()
    }
}