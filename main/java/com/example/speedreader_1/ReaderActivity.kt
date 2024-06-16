package com.example.speedreader_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.*

class ReaderActivity : AppCompatActivity() {

    private lateinit var mainLabel: TextView
    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reader)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        settingsManager = SettingsManager(this)
        val speedMin = settingsManager.speedMin
        val speedMax = settingsManager.speedMax

        val buttonBack: Button = findViewById(R.id.button_stop)
        mainLabel = findViewById(R.id.textView)
        val userInput = intent.getStringExtra("USER_INPUT") ?: ""

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        GlobalScope.launch {
            func(userInput, speedMin, speedMax)
        }
    }

    private suspend fun func(s: String, speedMin: Int, speedMax: Int) {
        withContext(Dispatchers.Main) {
            mainLabel.text = "Внимание!!!"
            delay(1000)
            mainLabel.text = "3"
            delay(1000)
            mainLabel.text = "2"
            delay(1000)
            mainLabel.text = "1"
            delay(1000)
            mainLabel.text = ""
        }

        val words = s.split(" ")
        val count = words.size
        val cntUp = count / 5
        val add = (speedMax - speedMin) / 5
        var speed = speedMin
        var cnt = 0
        for (word in words) {
            withContext(Dispatchers.Main) {
                mainLabel.text = word
            }
            delay((60*1000 / speed).toLong())

            cnt++
            if (cnt >= cntUp) {
                cnt = 0
                speed += add
                if (speed >= speedMax) {
                    speed = speedMax
                }
            }
        }
        mainLabel.text = ""
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
