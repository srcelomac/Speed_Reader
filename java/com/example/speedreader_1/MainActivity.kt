package com.example.speedreader_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val insert_field: EditText = findViewById(R.id.ins_field)
        val button_start: Button = findViewById(R.id.button_start)
        val button_settings: Button = findViewById(R.id.button_settings)
        val button_clean: Button = findViewById(R.id.button_clean)

        button_start.setOnClickListener {
            val user_text = insert_field.text.toString().trim()
            if (user_text == "")
                Toast.makeText(this, "Поле пустое", Toast.LENGTH_SHORT).show()
            else {
                val user_input: String = insert_field.text.toString()
                val intent = Intent(this, ReaderActivity::class.java)
                intent.putExtra("USER_INPUT", user_input)
                startActivity(intent)
            }
        }

        button_clean.setOnClickListener {
            insert_field.text.clear()
        }

        button_settings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }



    }
}