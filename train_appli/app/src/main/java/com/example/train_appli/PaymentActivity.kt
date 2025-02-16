package com.example.train_appli

import Train
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Récupérer la liste du panier passée par l'intent
        val cartList = intent.getSerializableExtra("cartList") as ArrayList<Train>

        // Afficher un résumé du panier (exemple basique)
        val summaryTextView = findViewById<TextView>(R.id.summaryTextView)
        val summary = cartList.joinToString("\n") { train ->
            "${train.departureCity} → ${train.arrivalCity} - ${train.price}"
        }
        summaryTextView.text = summary
    }
}