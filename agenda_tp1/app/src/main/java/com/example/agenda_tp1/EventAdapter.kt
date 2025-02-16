package com.example.agenda_tp1
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private var events: MutableList<String>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTextView: TextView = itemView.findViewById(R.id.eventTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.eventTextView.text = event // Affiche l'événement avec l'heure
    }

    override fun getItemCount(): Int {
        return events.size
    }

    // Méthode pour mettre à jour la liste des événements
    @SuppressLint("NotifyDataSetChanged")
    fun updateEvents(newEvents: List<String>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged()
    }
}
