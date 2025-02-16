package com.example.agenda_tp1

import java.time.LocalDate

data class Event(
    val title: String,
    val date: LocalDate,
    val description: String,
    val time: String // Ajouter un champ pour l'heure
)
