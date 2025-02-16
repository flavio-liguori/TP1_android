import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.train_appli.R
import com.google.android.material.card.MaterialCardView

class TrainAdapter(
    private var trainList: List<Train>, // Correction ici (List au lieu de String)
    private val context: Context,
    private val onAddToCart: (Train) -> Unit,
    private val isClickable: Boolean
) : RecyclerView.Adapter<TrainAdapter.TrainViewHolder>() {

    class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val departureTime: TextView = itemView.findViewById(R.id.departureTime)
        val departureCity: TextView = itemView.findViewById(R.id.departureCity)
        val arrivalTime: TextView = itemView.findViewById(R.id.arrivalTime)
        val arrivalCity: TextView = itemView.findViewById(R.id.arrivalCity)
        val travelTime: TextView = itemView.findViewById(R.id.travelTime)
        val trainPrice: TextView = itemView.findViewById(R.id.trainPrice)
        val trainType: TextView = itemView.findViewById(R.id.trainType)
        val cardView: MaterialCardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_train_route, parent, false)
        return TrainViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val train = trainList[position] // ✅ Correction

        holder.departureTime.text = train.departureTime
        holder.departureCity.text = train.departureCity
        holder.arrivalTime.text = train.arrivalTime
        holder.arrivalCity.text = train.arrivalCity
        holder.travelTime.text = train.travelTime
        holder.trainPrice.text = train.price
        holder.trainType.text = train.trainType

        // Gérer les clics selon isClickable
        if (isClickable) {
            holder.cardView.setOnClickListener {
                showAddToCartDialog(train)
            }
        } else {
            holder.cardView.isClickable = false
        }
    }

    override fun getItemCount(): Int {
        return trainList.size // ✅ Correction
    }

    private fun showAddToCartDialog(train: Train) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Ajouter au panier")
            .setMessage("Voulez-vous ajouter ce train (${train.trainType} de ${train.departureCity} à ${train.arrivalCity}) à votre panier ?")
            .setPositiveButton("Ajouter") { dialog, _ ->
                onAddToCart(train)
                Toast.makeText(context, "Train ajouté au panier !", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Annuler") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(
            GradientDrawable().apply {
                setColor(ContextCompat.getColor(context, R.color.backgroundColor))
                cornerRadius = 50f
            }
        )
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(context.getColor(R.color.white))
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(context.getColor(R.color.white))
        alertDialog.findViewById<TextView>(android.R.id.message)?.setTextColor(context.getColor(R.color.white))
        alertDialog.findViewById<TextView>(context.resources.getIdentifier("alertTitle", "id", "android"))?.setTextColor(context.getColor(R.color.white))
    }
    fun updateData(newTrainList: List<Train>) {
        this.trainList = newTrainList
        notifyDataSetChanged() // Notifier l'adaptateur du changement de données
    }
}
