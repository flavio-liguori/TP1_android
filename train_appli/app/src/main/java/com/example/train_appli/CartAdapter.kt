package com.example.train_appli

import Train
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val trainList: List<Train>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val departureTime: TextView = itemView.findViewById(R.id.departureTime)
        val departureCity: TextView = itemView.findViewById(R.id.departureCity)
        val arrivalTime: TextView = itemView.findViewById(R.id.arrivalTime)
        val arrivalCity: TextView = itemView.findViewById(R.id.arrivalCity)
        val trainType: TextView = itemView.findViewById(R.id.trainType)
        val travelTime: TextView = itemView.findViewById(R.id.travelTime)
        val trainPrice: TextView = itemView.findViewById(R.id.trainPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.basket_list, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val train = trainList[position]
        holder.departureTime.text = train.departureTime
        holder.departureCity.text = train.departureCity
        holder.arrivalTime.text = train.arrivalTime
        holder.arrivalCity.text = train.arrivalCity
        holder.trainType.text = train.trainType
        holder.travelTime.text = train.travelTime
        holder.trainPrice.text = train.price
    }

    override fun getItemCount(): Int = trainList.size
}