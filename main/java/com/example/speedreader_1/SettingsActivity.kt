package com.example.speedreader_1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsManager: SettingsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val label_1: TextView = findViewById(R.id.textView_main)
        val label_min: TextView = findViewById(R.id.textView_min)
        val label_max: TextView = findViewById(R.id.textView_max)


        settingsManager = SettingsManager(this)

        val editText_min: EditText = findViewById(R.id.editText_min)
        val editText_max: EditText = findViewById(R.id.editText_max)
        editText_min.setText(settingsManager.speedMin.toString())
        editText_max.setText(settingsManager.speedMax.toString())

        val button_save: Button = findViewById(R.id.button_save)
        val button_back: Button = findViewById(R.id.button_back)


        button_save.setOnClickListener {
            val speedMin = editText_min.text.toString().toIntOrNull() ?: 250
            val speedMax = editText_max.text.toString().toIntOrNull() ?: 750
            if (speedMin > speedMax)
                Toast.makeText(this, "Минимальная скорость не может быть больше максимальное", Toast.LENGTH_LONG).show()
            else {
                settingsManager.speedMin = speedMin
                settingsManager.speedMax = speedMax
                Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        button_back.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}