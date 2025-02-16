package com.example.tp1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        val validerButton = findViewById<Button>(R.id.validerButton)

        validerButton.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Voulez-vous vraiment valider ce formulaire ?")
            .setPositiveButton("Oui") { dialog, _ ->
                dialog.dismiss() // Ferme la boîte de dialogue
                sendDataToSecondActivity()
            }
            .setNegativeButton("Non") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }

    private fun sendDataToSecondActivity() {
        // Récupération des valeurs des champs
        val nom = findViewById<EditText>(R.id.nomEditText).text.toString()
        val prenom = findViewById<EditText>(R.id.prenomEditText).text.toString()
        val age = findViewById<EditText>(R.id.ageEditText).text.toString()
        val domaine = findViewById<EditText>(R.id.domaineEditText).text.toString()
        val numero = findViewById<EditText>(R.id.telephoneEditText).text.toString()

        // Création de l'intent pour passer à SecondActivity
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("nom", nom)
            putExtra("prenom", prenom)
            putExtra("age", age)
            putExtra("domaine", domaine)
            putExtra("numero", numero)
        }

        startActivity(intent)
    }
}
