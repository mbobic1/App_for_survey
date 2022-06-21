package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Odgovor


@Dao
interface IstrazivanjeDao {

    @Query("DELETE FROM istrazivanje")
    suspend fun deleteTable()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIstrazivanje(vararg istrazivanje: Istrazivanje)
}