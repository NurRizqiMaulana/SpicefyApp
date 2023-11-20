package com.dicoding.spicefyapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.spicefyapp.data.local.Spice
import com.dicoding.spicefyapp.data.local.SpiceDao
import com.dicoding.spicefyapp.data.local.SpiceRoomDatabase

class SpiceRepository(application: Application) {

    private val mSpiceDao: SpiceDao

    init {
        val db = SpiceRoomDatabase.getDatabase(application)
        mSpiceDao = db.spiceDao()
    }

    fun getSpiceDetail(id: Int): LiveData<Spice?> = mSpiceDao.getSpiceDetail(id)

    companion object {
        @Volatile
        private var INSTANCE: SpiceRepository? = null

        fun getInstance(application: Application): SpiceRepository {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = SpiceRepository(application)
                }
                return INSTANCE as SpiceRepository
            }
        }
    }



}