package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Odgovor


@Dao
interface OdgovorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOdgovor(vararg odgovor : Odgovor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOdgovori(odgovori : List<Odgovor>)

    @Query("DELETE FROM odgovor")
    suspend fun deleteTable()

    @Query("SELECT * FROM odgovor")
    suspend fun getAllOdgovori():List<Odgovor>

    @Query("SELECT * FROM odgovor WHERE anketaId=:anketaId")
    suspend fun getOdgovori(vararg anketaId : Int) : List<Odgovor>

    @Query("SELECT COUNT(*) FROM odgovor")
    suspend fun dajBrojOdgovora():Int

}