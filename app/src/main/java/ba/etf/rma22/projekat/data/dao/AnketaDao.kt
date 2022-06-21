package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Odgovor


@Dao
interface AnketaDao {

    @Query("SELECT * FROM anketa")
    suspend fun getAllAnkete(): List<Anketa>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnketa(vararg anketa : Anketa)

    @Query("UPDATE anketa SET predan=1 WHERE id=:id")
    suspend fun predajAnketa(vararg  id:Int)


    @Query("DELETE FROM anketa")
    suspend fun deleteTable()
}