package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa


@Dao
interface GrupaDao {

    @Query("SELECT * FROM grupa")
    suspend fun getAllGroups(): List<Grupa>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrupa(vararg grupa : Grupa)

    @Query("DELETE FROM grupa")
    suspend fun deleteTable()
}