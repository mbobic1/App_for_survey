package ba.etf.rma22.projekat.data.models

import android.os.Message
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity
data class Anketa(
    @PrimaryKey @SerializedName("id") var id : Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") var naziv: String,
    var nazivIstrazivanja: String?,
    @ColumnInfo(name = "datumPocetak") @SerializedName("datumPocetak") var datumPocetak: Date?,
    @ColumnInfo(name = "datumKraj") @SerializedName("datumKraj") var datumKraj: Date?,
    var datumRada: Date?,
    @ColumnInfo(name = "trajanje") @SerializedName("trajanje") var trajanje: Int,
    var nazivGrupe: String?,
    var progres: Int?,
    @ColumnInfo(name = "message") @SerializedName("message") var message: String?,
    @ColumnInfo(name = "predan") var predan : Boolean = false
    ):Serializable
