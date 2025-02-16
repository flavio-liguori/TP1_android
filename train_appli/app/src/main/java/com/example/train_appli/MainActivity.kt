package com.example.train_appli

import Train
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {

    private val cartList = mutableListOf<Train>() // Liste pour stocker les trajets ajoutés au panier

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        // Récupérer la liste du panier passée par l'intent (si elle existe)
        val receivedCartList = intent.getSerializableExtra("cartList") as? ArrayList<Train>
        if (receivedCartList != null) {
            cartList.addAll(receivedCartList) // Ajouter les éléments reçus à la liste locale
        }

        val editTextDeparture = findViewById<AutoCompleteTextView>(R.id.editTextDeparture)
        val editTextArrival = findViewById<AutoCompleteTextView>(R.id.editTextArrival)
        val btnValidate = findViewById<Button>(R.id.btnValidate)
        val datePickerComposeView = findViewById<ComposeView>(R.id.datePickerComposeView)

        var selectedDate: String? = null

        // Ajout de l'auto-complétion avec une liste statique
        setupAutoComplete(editTextDeparture, this)
        setupAutoComplete(editTextArrival, this)

        // Ajout du sélecteur de date en Jetpack Compose
        datePickerComposeView?.setContent {
            TrainAppTheme {
                DatePickerScreen { date ->
                    selectedDate = date
                }
            }
        }

        btnValidate.setOnClickListener {
            val departure = editTextDeparture.text.toString()
            val arrival = editTextArrival.text.toString()

            if (selectedDate.isNullOrEmpty()) {
                selectedDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
            }

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("DEPARTURE", departure)
                putExtra("ARRIVAL", arrival)
                putExtra("DATE", selectedDate)
                putExtra("cartList", ArrayList(cartList)) // Envoyer la liste du panier
            }
            startActivity(intent)
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

    // Auto-complétion avec une liste fixe
    private fun setupAutoComplete(autoCompleteTextView: AutoCompleteTextView, context: Context) {
        val gares = listOf("Paris", "Lyon", "Marseille", "Bordeaux", "Toulouse", "Nice", "Nantes", "Strasbourg")
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, gares)
        autoCompleteTextView.setAdapter(adapter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun DatePickerScreen(onDateSelected: (String) -> Unit) {
        var selectedDate by remember { mutableStateOf("Date départ") }
        var showDatePicker by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { showDatePicker = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0D151D),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth().height(70.dp)
            ) {
                Text("Départ: $selectedDate")
            }
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                onDateSelected = { date ->
                    selectedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    onDateSelected(selectedDate)
                    showDatePicker = false
                }
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerDialog(
        onDismissRequest: () -> Unit,
        onDateSelected: (LocalDate) -> Unit
    ) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

        AlertDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(onClick = {
                    val selectedDateMillis = datePickerState.selectedDateMillis
                    if (selectedDateMillis != null) {
                        onDateSelected(LocalDate.ofEpochDay(selectedDateMillis / 86400000))
                    }
                    onDismissRequest()
                }) {
                    Text("OK", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("Annuler", color = Color.White)
                }
            },
            title = { Text("Sélectionnez une date", color = Color.White) },
            text = { DatePicker(state = datePickerState) },
            containerColor = Color(0xFF071018),
            tonalElevation = 8.dp
        )
    }

    @Composable
    fun TrainAppTheme(content: @Composable () -> Unit) {
        MaterialTheme(
            colorScheme = lightColorScheme(),
            typography = Typography(),
            content = content
        )
    }
}