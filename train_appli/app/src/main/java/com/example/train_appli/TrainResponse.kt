import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class TrainResponse(val trains: List<Train>) // Modèle pour les données JSON

suspend fun fetchTrainData(): List<Train>? {
    val apiKey = "d54cb85a-5b41-411a-9280-4e7600d47e73"
    val url = "https://api.sncf.com/v1/coverage/sncf/journeys" // Exemple d'URL de l'API SNCF

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .addHeader("Authorization", apiKey)
        .build()

    return withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                val gson = Gson()
                val trainResponse = gson.fromJson(responseBody, TrainResponse::class.java)
                trainResponse.trains
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
