package com.example.agenda_tp1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val events = mutableMapOf<String, MutableList<String>>() // Map pour stocker les événements
    private lateinit var selectedDate: String // Date actuellement sélectionnée
    private lateinit var eventAdapter: EventAdapter // Adaptateur pour la RecyclerView
    private lateinit var eventRecyclerView: RecyclerView // RecyclerView pour afficher les événements
    private lateinit var noEventsTextView: TextView // TextView pour afficher le message lorsqu'il n'y a pas d'événements

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val addEventButton: FloatingActionButton = findViewById(R.id.addEventButton)
        eventRecyclerView = findViewById(R.id.eventRecyclerView)
        noEventsTextView = findViewById(R.id.noEventsTextView)

        // Initialisation de la date sélectionnée avec la date actuelle
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        selectedDate = sdf.format(Date(calendarView.date))

        // Initialisation de la RecyclerView
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter(mutableListOf()) // Initialisation avec une liste vide
        eventRecyclerView.adapter = eventAdapter

        // Gestion de la sélection d'une nouvelle date
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
            updateEventList()
        }

        // Gestion du clic sur le bouton "Ajouter un événement"
        addEventButton.setOnClickListener {
            showAddEventDialog()
        }
    }

    /**
     * Affiche une boîte de dialogue pour ajouter un événement à la date sélectionnée.
     */
    @SuppressLint("Range")
    private fun showAddEventDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_event, null)
        val descriptionEditText = dialogView.findViewById<android.widget.EditText>(R.id.descriptionEditText)
        val selectedTimeTextView = dialogView.findViewById<android.widget.TextView>(R.id.selectedTimeTextView)
        val selectTimeButton = dialogView.findViewById<android.widget.Button>(R.id.selectTimeButton)

        var selectedHour = -1  // Définis ces variables ici pour qu'elles soient accessibles partout dans la fonction
        var selectedMinute = -1

        // Gestion du clic sur le bouton pour ouvrir le Time Picker
        selectTimeButton.setOnClickListener {
            val timePicker = com.google.android.material.timepicker.MaterialTimePicker.Builder()
                .setTimeFormat(com.google.android.material.timepicker.TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Sélectionnez l'heure")
                .build()

            timePicker.show(supportFragmentManager, "timePicker")

            // Mettre à jour les variables selectedHour et selectedMinute lorsque l'utilisateur choisit l'heure
            timePicker.addOnPositiveButtonClickListener {
                selectedHour = timePicker.hour
                selectedMinute = timePicker.minute
                selectedTimeTextView.text = "%02d:%02d".format(selectedHour, selectedMinute)  // Affiche l'heure sélectionnée
            }
        }

        // Afficher la boîte de dialogue
        AlertDialog.Builder(this)
            .setTitle("Ajouter un événement")
            .setView(dialogView)
            .setPositiveButton("Ajouter") { _, _ ->
                val description = descriptionEditText.text.toString()

                if (description.isNotEmpty() && selectedHour != -1 && selectedMinute != -1) {
                    val eventTime = "%02d:%02d".format(selectedHour, selectedMinute)
                    val eventDescription = "$description à $eventTime"

                    // Ajouter l'événement à la liste pour la date sélectionnée
                    events.getOrPut(selectedDate) { mutableListOf() }.add(eventDescription)

                    updateEventList()

                    Toast.makeText(this, "Événement ajouté pour le $selectedDate", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Veuillez entrer une description et sélectionner une heure", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Annuler", null)
            .create()
            .show()
    }

    /**
     * Met à jour la liste des événements affichés dans la RecyclerView pour la date sélectionnée.
     */
    private fun updateEventList() {
        // Récupérer les événements pour la date sélectionnée
        val eventList = events[selectedDate] ?: mutableListOf()

        // Mettre à jour l'adaptateur avec les nouveaux événements
        eventAdapter.updateEvents(eventList)

        // Afficher ou masquer le message "Il n'y a aucun événement"
        if (eventList.isEmpty()) {
            noEventsTextView.visibility = View.VISIBLE
        } else {
            noEventsTextView.visibility = View.GONE
        }
    }
}