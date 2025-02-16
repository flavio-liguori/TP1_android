import android.os.Parcel
import android.os.Parcelable

data class Train(
    val departureTime: String,
    val departureCity: String,
    val arrivalTime: String,
    val arrivalCity: String,
    val travelTime: String,
    val price: String,
    val trainType: String,
    val date: String // Ajout du champ date
) : Parcelable {
    constructor(parcel: Parcel) : this(
        departureTime = parcel.readString() ?: "",
        departureCity = parcel.readString() ?: "",
        arrivalTime = parcel.readString() ?: "",
        arrivalCity = parcel.readString() ?: "",
        travelTime = parcel.readString() ?: "",
        price = parcel.readString() ?: "",
        trainType = parcel.readString() ?: "",
        date = parcel.readString() ?: "" // Lecture du champ date
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(departureTime)
        parcel.writeString(departureCity)
        parcel.writeString(arrivalTime)
        parcel.writeString(arrivalCity)
        parcel.writeString(travelTime)
        parcel.writeString(price)
        parcel.writeString(trainType)
        parcel.writeString(date) // Écriture du champ date
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Train> {
        override fun createFromParcel(parcel: Parcel): Train {
            return Train(parcel)
        }

        override fun newArray(size: Int): Array<Train?> {
            return arrayOfNulls(size)
        }
    }
}
