package com.dicoding.spicefyapp.ui.dashboard.spicelib

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.spicefyapp.databinding.ActivitySpiceLibBinding
import com.dicoding.spicefyapp.model.FakeSpiceData
import com.dicoding.spicefyapp.model.SpiceModel
import com.dicoding.spicefyapp.ui.dashboard.adapter.SpicelibListAdapter
import java.util.Locale

class SpiceLibActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpiceLibBinding
    private var mList = ArrayList<SpiceModel>()
    private lateinit var adapter: SpicelibListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpiceLibBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = SpicelibListAdapter(mList)
        binding.recyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun addDataToList() {
        mList.addAll(FakeSpiceData.spice)
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<SpiceModel>()
            for (i in mList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }
}