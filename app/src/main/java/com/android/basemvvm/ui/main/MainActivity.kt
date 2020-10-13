package com.android.basemvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.basemvvm.empty.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}