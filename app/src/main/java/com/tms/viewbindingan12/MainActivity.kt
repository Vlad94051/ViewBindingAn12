package com.tms.viewbindingan12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tms.viewbindingan12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            progress.setOnClickListener {
                progress.startAnimation(progressValue.text.toString().toFloat())
            }
        }
    }
}