package com.example.train_appli

import Train
import TrainAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : ComponentActivity() {

    private val cartList = mutableListOf<Train>() // Liste pour stocker les trajets ajoutés au panier

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Récupérer les boutons
        val searchButton = findViewById<ImageButton>(R.id.searchButton)
        val fabCart = findViewById<ImageButton>(R.id.fabCart)
        val accountButton = findViewById<ImageButton>(R.id.accountButton)
        val receivedCartList = intent.getSerializableExtra("cartList") as? ArrayList<Train>
        if (receivedCartList != null) {
            cartList.addAll(receivedCartList) // Ajouter les éléments reçus à la liste locale
        }

        // Redirection vers MainActivity (Recherche) avec cartList
        searchButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Passer la liste du panier
            startActivity(intent)
        }

        // Redirection vers ProfilActivity (Profil) avec cartList
        accountButton.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Passer la liste du panier
            startActivity(intent)
        }

        // Gestion du bouton du panier
        fabCart.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("cartList", ArrayList(cartList)) // Passer la liste du panier
            startActivity(intent)
        }

        // Récupérer les données de l'intent
        val departure = intent.getStringExtra("DEPARTURE") ?: "Non spécifié"
        val arrival = intent.getStringExtra("ARRIVAL") ?: "Non spécifié"
        val date = intent.getStringExtra("DATE") ?: "Non spécifiée"

        // Initialiser le RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.trainRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Créer une liste de trains en dur
        val trainList = listOf(
            Train("10:00", "Paris Gare de Lyon", "12:30", "Lyon Part-Dieu", "2h30", "50 €", "TGV", "2025-02-16"),
            Train("11:15", "Paris Montparnasse", "14:00", "Bordeaux Saint-Jean", "2h45", "40 €", "TGV", "2025-02-16"),
            Train("12:30", "Paris Nord", "13:15", "Lille Europe", "0h45", "20 €", "TER", "2025-02-16"),
            Train("14:00", "Paris Est", "16:30", "Strasbourg", "2h30", "60 €", "TGV", "2025-02-16"),
            Train("15:45", "Paris Saint-Lazare", "17:00", "Rouen Rive Droite", "1h15", "15 €", "TER", "2025-02-16"),
            Train("08:00", "Lyon Part-Dieu", "10:30", "Paris Gare de Lyon", "2h30", "50 €", "TGV", "2025-02-16"),
            Train("09:30", "Lyon Part-Dieu", "12:00", "Marseille Saint-Charles", "2h30", "45 €", "TGV", "2025-02-16"),
            Train("10:00", "Bordeaux Saint-Jean", "12:45", "Paris Montparnasse", "2h45", "40 €", "TGV", "2025-02-16"),
            Train("13:00", "Lille Europe", "13:45", "Paris Nord", "0h45", "20 €", "TER", "2025-02-16"),
            Train("16:00", "Strasbourg", "18:30", "Paris Est", "2h30", "60 €", "TGV", "2025-02-16"),
            Train("17:30", "Rouen Rive Droite", "18:45", "Paris Saint-Lazare", "1h15", "15 €", "TER", "2025-02-16"),
            Train("07:00", "Paris Gare de Lyon", "09:30", "Lyon Part-Dieu", "2h30", "50 €", "TGV", "2025-02-17"),
            Train("08:15", "Paris Montparnasse", "11:00", "Bordeaux Saint-Jean", "2h45", "40 €", "TGV", "2025-02-17"),
            Train("09:30", "Paris Nord", "10:15", "Lille Europe", "0h45", "20 €", "TER", "2025-02-17"),
            Train("11:00", "Paris Est", "13:30", "Strasbourg", "2h30", "60 €", "TGV", "2025-02-17"),
            Train("12:45", "Paris Saint-Lazare", "14:00", "Rouen Rive Droite", "1h15", "15 €", "TER", "2025-02-17"),
            Train("07:30", "Lyon Part-Dieu", "10:00", "Paris Gare de Lyon", "2h30", "50 €", "TGV", "2025-02-17"),
            Train("08:00", "Lyon Part-Dieu", "10:30", "Marseille Saint-Charles", "2h30", "45 €", "TGV", "2025-02-17"),
            Train("09:00", "Bordeaux Saint-Jean", "11:45", "Paris Montparnasse", "2h45", "40 €", "TGV", "2025-02-17"),
            Train("12:00", "Lille Europe", "12:45", "Paris Nord", "0h45", "20 €", "TER", "2025-02-17"),
            Train("15:00", "Strasbourg", "17:30", "Paris Est", "2h30", "60 €", "TGV", "2025-02-17"),
            Train("16:30", "Rouen Rive Droite", "17:45", "Paris Saint-Lazare", "1h15", "15 €", "TER", "2025-02-17")
        )

        // Filtrer les trains en fonction de la gare de départ, de la gare d'arrivée et de la date
        val filteredTrains = trainList.filter { train ->
            train.departureCity.contains(departure, ignoreCase = true) &&
                    train.arrivalCity.contains(arrival, ignoreCase = true)
        }

        // Afficher un message si aucun train n'est trouvé
        if (filteredTrains.isEmpty()) {
            Toast.makeText(this, "Aucun train trouvé pour votre recherche", Toast.LENGTH_SHORT).show()
        }

        // Initialiser l'adaptateur avec la liste filtrée de trains
        val adapter = TrainAdapter(
            filteredTrains, // Liste filtrée de trains
            context = this,
            onAddToCart = { train ->
                cartList.add(train) // Ajouter le train au panier
                Toast.makeText(this, "Ajouté au panier : ${train.departureCity} → ${train.arrivalCity}", Toast.LENGTH_SHORT).show()
            },
            isClickable = true
        )
        recyclerView.adapter = adapter

        // Gestion du filtre TGV
        val filterTGVButton = findViewById<Button>(R.id.filterTGVButton)
        filterTGVButton.setOnClickListener {
            val tgvTrains = trainList.filter { it.trainType == "TGV" }
            val filteredTGVTrains = tgvTrains.filter { train ->
                train.departureCity.contains(departure, ignoreCase = true) &&
                        train.arrivalCity.contains(arrival, ignoreCase = true)
            }
            updateRecyclerView(filteredTGVTrains)
        }

        // Gestion du filtre TER
        val filterTERButton = findViewById<Button>(R.id.filterTERButton)
        filterTERButton.setOnClickListener {
            val terTrains = trainList.filter { it.trainType == "TER" }
            val filteredTERTrains = terTrains.filter { train ->
                train.departureCity.contains(departure, ignoreCase = true) &&
                        train.arrivalCity.contains(arrival, ignoreCase = true)
            }
            updateRecyclerView(filteredTERTrains)
        }
    }

    fun updateRecyclerView(filteredTrains: List<Train>) {
        val recyclerView = findViewById<RecyclerView>(R.id.trainRecyclerView)
        val adapter = TrainAdapter(
            filteredTrains, // Liste filtrée de trains
            context = this,
            onAddToCart = { train ->
                cartList.add(train) // Ajouter le train au panier
                Toast.makeText(this, "Ajouté au panier : ${train.departureCity} → ${train.arrivalCity}", Toast.LENGTH_SHORT).show()
            },
            isClickable = true
        )
        recyclerView.adapter = adapter
    }
}