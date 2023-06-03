package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class OdgovorPitanje(
    @SerializedName("odgovor") var odgovor : Int,
    @SerializedName("pitanje") var pitnaje : Int,
    @SerializedName("progres") var progres : Int
)
