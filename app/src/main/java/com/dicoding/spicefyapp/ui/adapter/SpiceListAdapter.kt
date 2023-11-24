package com.dicoding.spicefyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.spicefyapp.databinding.ItemRowSpiceBinding
import com.dicoding.spicefyapp.model.SpiceModel

class SpiceListAdapter(private var listSpice: List<SpiceModel>) : RecyclerView.Adapter<SpiceListAdapter.SpiceViewHolder>() {
    class SpiceViewHolder(binding: ItemRowSpiceBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageSpice = binding.imgSpice
        val tvTitleSpice = binding.titleSpice
        val tvDescSpice = binding.descSpice


        fun bind(spice: SpiceModel) {
            imageSpice.setImageResource(spice.picture)
            tvTitleSpice.text = spice.name
            tvDescSpice.text = spice.description

//            itemView.setOnClickListener{
//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra(DetailActivity.EXTRA_Tour, tours)
//                itemView.context.startActivity(intent)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpiceViewHolder {
        val binding = ItemRowSpiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpiceViewHolder(binding)
    }

    override fun getItemCount(): Int =listSpice.size

    override fun onBindViewHolder(holder: SpiceViewHolder, position: Int) {
        holder.bind(listSpice[position])

    }

    fun searchDataList(searchList : List <SpiceModel>){
        listSpice = searchList
        notifyDataSetChanged()

    }

}