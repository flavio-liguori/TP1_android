package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Récupérer les données envoyées par MainActivity
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val age = intent.getStringExtra("age")
        val domaine = intent.getStringExtra("domaine")
        val numero = intent.getStringExtra("numero") // Récupération du numéro

        // Récupérer les TextView et boutons
        val textViewData: TextView = findViewById(R.id.textViewData)
        val buttonOk: Button = findViewById(R.id.buttonOk)
        val buttonRetour: Button = findViewById(R.id.buttonRetour)

        // Affichage des données
        val message = """
            Nom: $nom
            Prénom: $prenom
            Âge: $age
            Domaine: $domaine
            Numéro: $numero
        """.trimIndent()
        textViewData.text = message

        // Bouton "OK" pour ouvrir ThirdActivity en passant le numéro de téléphone
        buttonOk.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("numero", numero) // Passage du numéro dans l'intent
            startActivity(intent)
        }

        // Bouton "Retour" pour fermer SecondActivity et revenir à MainActivity
        buttonRetour.setOnClickListener {
            finish()
        }
    }
}
