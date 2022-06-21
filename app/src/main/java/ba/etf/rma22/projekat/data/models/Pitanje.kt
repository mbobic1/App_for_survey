package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Pitanje(
    @PrimaryKey @SerializedName("id") val id:Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv : String,
    @ColumnInfo(name = "tekstPitanja") @SerializedName("tekstPitanja")val tekstPitanja : String,
    @ColumnInfo(name = "opcije") @SerializedName("opcije") val opcije : List<String>,
    @ColumnInfo(name = "anketaId") @SerializedName("anketaId") val anketaId : Int
)