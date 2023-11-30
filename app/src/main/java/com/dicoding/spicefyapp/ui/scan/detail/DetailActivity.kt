package com.dicoding.spicefyapp.ui.scan.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.spicefyapp.databinding.ActivityDetailBinding
import com.dicoding.spicefyapp.data.model.PredictResponse

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val predictResult = intent.getParcelableExtra<PredictResponse>(PREDICT_RESULT) as PredictResponse

        detailViewModel = ViewModelProvider(this,
            DetailViewModelFactory.getInstance(application)
        )[DetailViewModel::class.java]
        detailViewModel.getBatikRandom(predictResult.id).observe(this) {
            if (it != null) {
                binding.ivDetailImage.setImageBitmap(predictResult.image)
                binding.tvDetailNameContent.text = it.name
                binding.tvDetailScoreContent.text = "${predictResult.confidence}%"
                binding.tvDetailDescriptionContent.text = it.description
            }
        }
    }

    companion object {
        const val PREDICT_RESULT = "predict_result"
    }
}