package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id") var id : Int,
    @SerializedName("student") var student : String,
    @SerializedName("acHash") var acHash : String
)
