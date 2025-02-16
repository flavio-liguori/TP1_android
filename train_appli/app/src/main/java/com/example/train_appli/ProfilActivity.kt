package com.example.train_appli

import Train
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity

class ProfilActivity : ComponentActivity() {

    private val cartList = mutableListOf<Train>() // Liste pour stocker les trajets ajoutés au panier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        // Récupérer la liste du panier passée par l'intent (si elle existe)
        val receivedCartList = intent.getSerializableExtra("cartList") as? ArrayList<Train>
        if (receivedCartList != null) {
            cartList.addAll(receivedCartList) // Ajouter les éléments reçus à la liste locale
        }

        // Récupérer les vues
        val textMail = findViewById<TextView>(R.id.textMail)
        val textPhone = findViewById<TextView>(R.id.textPhone)
        val textFirstName = findViewById<TextView>(R.id.textFirstName)
        val textLastName = findViewById<TextView>(R.id.textLastName)
        val textBirthDate = findViewById<TextView>(R.id.textBirthDate)
        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)

        // Définir les informations du profil
        textMail.text = "test@gmail.com"
        textPhone.text = "0758465241"
        textFirstName.text = "Flavio"
        textLastName.text = "Liguori"
        textBirthDate.text = "24/08/2002"

        // Gestion du bouton "Modifier le profil"
        btnEditProfile.setOnClickListener {
            // Ajouter ici la logique pour modifier le profil
            // Par exemple, ouvrir une nouvelle activité ou un dialogue
        }

        // Récupérer les boutons
        val searchButton = findViewById<ImageButton>(R.id.searchButton)
        val fabCart = findViewById<ImageButton>(R.id.fabCart)
        val accountButton = findViewById<ImageButton>(R.id.accountButton)

        // Redirection vers MainActivity (Recherche) avec cartList
        searchButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Envoyer la liste du panier
            startActivity(intent)
        }

        // Redirection vers ProfilActivity (Profil) avec cartList
        accountButton.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Envoyer la liste du panier
            startActivity(intent)
        }

        // Gestion du bouton du panier avec cartList
        fabCart.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Passer la liste du panier
            startActivity(intent)
        }
    }
}