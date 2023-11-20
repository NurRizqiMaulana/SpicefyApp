package com.dicoding.spicefyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.spicefyapp.data.local.Spice
import com.dicoding.spicefyapp.repository.SpiceRepository

class DetailViewModel(private val repository: SpiceRepository) : ViewModel() {
    fun getBatikRandom(id: Int): LiveData<Spice?> = repository.getSpiceDetail(id)
}