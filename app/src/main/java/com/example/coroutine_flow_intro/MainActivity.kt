package com.example.coroutine_flow_intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Producer
        val myFlow = flow {
            for (i in 1..100) {
                emit(i)
                delay(1000L)
            }
        }

        // Collector
        val textView = findViewById<TextView>(R.id.tvResult)
        CoroutineScope(Main).launch {
            myFlow.collect {
                val resultTv = "Current index is $it"
                textView.text = resultTv
            }
        }
    }
}