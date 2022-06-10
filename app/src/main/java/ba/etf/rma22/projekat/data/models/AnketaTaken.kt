package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class AnketaTaken (
    @SerializedName("id") val id : Int,
    @SerializedName("student") val student : String,
    @SerializedName("progres") val progres : Int,
    @SerializedName("datumRada") var datumRada : Date,
    @SerializedName("AnketumId") var AnketumId : Int
    )