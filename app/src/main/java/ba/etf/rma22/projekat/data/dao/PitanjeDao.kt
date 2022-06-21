package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Pitanje


@Dao
interface PitanjeDao {
    @Query("SELECT * FROM pitanje WHERE anketaId = :anketaId")
    suspend fun getPitanjaForAnketa(anketaId : Int) : List<Pitanje>

    @Query("DELETE FROM pitanje")
    suspend fun deleteTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPitanje(pitanja : List<Pitanje>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPitanjee(vararg pitanje : Pitanje)
}