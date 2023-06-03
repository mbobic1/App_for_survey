package ba.etf.rma22.projekat.data.repositories


import android.content.ContentValues.TAG
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

object AccountRepository {
        var acHash = "aa3328e0-6949-48d2-814e-80d7dd9a2a08"
        var context : Context?= null;

        suspend fun postaviHash(accHash: String): Boolean{
            return withContext(Dispatchers.IO){
                acHash=accHash
                return@withContext true
            }
        }

        suspend fun upisiAccUBazu() : Boolean{
            return withContext(Dispatchers.IO){
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val db = context?.let { AppDatabase.getInstance(it) }
                val br = db?.accountDao()?.getCheck()
                if (db != null) {
                       db.accountDao().deleteAccount()
                }
                if(br==0){
                    val acc = getStudent(getHash())
                    db.accountDao().insertAccount(Account(acc.id,acc.student,acc.acHash,format.format(
                        Calendar.getInstance().run { time })))
                    return@withContext true
                }
                return@withContext false
            }
        }



    fun getHash(): String {
            return acHash
        }

    suspend  fun getStudent(hash : String): Account {
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getStudnet(hash)
        }
    }
}