package com.avengers.energiaverde

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddTipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tip)

        val titleInput = findViewById<EditText>(R.id.editTextTitle)
        val descriptionInput = findViewById<EditText>(R.id.editTextDescription)
        val addButton = findViewById<Button>(R.id.buttonAdd)

        addButton.setOnClickListener {
            val title = titleInput.text.toString()
            val description = descriptionInput.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val resultIntent = intent
                resultIntent.putExtra("TIP_TITLE", title)
                resultIntent.putExtra("TIP_DESCRIPTION", description)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}