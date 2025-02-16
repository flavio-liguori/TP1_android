package com.example.tp1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        // Récupérer le numéro de téléphone passé par l'Intent
        val numero = intent.getStringExtra("numero") ?: "Numéro inconnu"

        // Récupérer les vues
        val imageViewPhone: ImageView = findViewById(R.id.imageViewPhone)
        val textViewNumero: TextView = findViewById(R.id.textViewNumero)
        val buttonAppeler: Button = findViewById(R.id.buttonAppeler)

        // Afficher le numéro
        textViewNumero.text = numero

        // Action du bouton "Appeler"
        buttonAppeler.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$numero")
            }
            startActivity(intent)
        }
    }
}
