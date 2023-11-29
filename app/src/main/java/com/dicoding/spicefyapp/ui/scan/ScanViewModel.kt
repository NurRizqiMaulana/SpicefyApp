package com.dicoding.spicefyapp.ui.scan

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScanViewModel : ViewModel() {
    private val _image = MutableLiveData<Bitmap?>()
    val image: LiveData<Bitmap?> = _image

    // LiveData untuk indikator loading
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        _image.value = null
        _loading.value = false
    }

    fun setImage(img: Bitmap?) {
        _image.value = img
    }

    // Fungsi untuk mengubah status indikator loading
    fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }
    fun resetLoadingStatus() {
        _loading.value = false
    }
}