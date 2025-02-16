package com.example.train_appli

import Train
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BasketActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var backButton: Button
    private lateinit var homeButton: Button
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Récupérer la liste du panier passée par l'intent
        val cartList = intent.getSerializableExtra("cartList") as ArrayList<Train>

        // Initialiser le RecyclerView et son adaptateur
        recyclerView = findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Passer la liste des trains au RecyclerView et définir l'adaptateur
        cartAdapter = CartAdapter(cartList)
        recyclerView.adapter = cartAdapter

        // Récupérer les boutons
        backButton = findViewById(R.id.backButton)
        val searchButton = findViewById<ImageButton>(R.id.searchButton)
        val fabCart = findViewById<ImageButton>(R.id.fabCart)
        val accountButton = findViewById<ImageButton>(R.id.accountButton)

        // Redirection vers MainActivity (Recherche)
        searchButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Redirection vers ProfilActivity (Profil)
        accountButton.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        // Gestion du bouton du panier
        fabCart.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Passer la liste du panier
            startActivity(intent)
        }
        // Bouton de retour
        backButton.setOnClickListener {
            finish() // Retour à l'activité précédente
        }

        // Bouton pour rediriger vers MainActivity

    }
}