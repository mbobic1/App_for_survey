package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account
@Dao
interface AccountDao {

    @Query("SELECT COUNT(*) FROM account")
    suspend fun getCheck():Int


    @Query("SELECT * FROM account")
    suspend fun getAccount(): Account

    @Query("DELETE FROM account")
    suspend fun deleteAccount()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(vararg account : Account)

    @Query("UPDATE account SET lastUpdate = :lastUpdate WHERE acHash = :accHash")
    suspend fun updateLastUpdateOnAcc(lastUpdate : String, accHash : String)

}