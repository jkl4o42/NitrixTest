package com.jkl.nitrixtesttask.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jkl.nitrixtesttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val networkHandler = NetworkHandler(this)
        networkHandler.observe(this) { status ->
            if (!status) {
                errorSnackbar = Snackbar.make(binding.root, "We lost connection", Snackbar.LENGTH_INDEFINITE)
                errorSnackbar?.show()
            } else {
                errorSnackbar?.dismiss()
                errorSnackbar = null
            }
        }
    }
}