package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object  IstrazivanjeIGrupaRepository {
    var context : Context?= null;
    suspend fun getIstrazivanja(i123 : Int):List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            var t = mutableListOf<Istrazivanje>()
            var k = ApiAdapter.retrofit.getSveIstrazivanja(i123)
            val db = AccountRepository.context?.let { AppDatabase.getInstance(it) }
            if (db != null) {
                for (i in k) {
                    db.istrazivanjeDao().insertIstrazivanje(i)
                }
            }

            return@withContext k
        }
    }

    suspend fun getIstrazivanja():List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            var t = mutableListOf<Istrazivanje>()
            var i=1;
            while (true){
                var k = ApiAdapter.retrofit.getSveIstrazivanja(i)
                if(k.size==0) break;
                t.addAll(k)
                i++
            }
            return@withContext t
        }
    }

    suspend fun getGrupe():List<Grupa>{
        return withContext(Dispatchers.IO){
            var k = ApiAdapter.retrofit.getSveGrupe()
            val db = AccountRepository.context?.let { AppDatabase.getInstance(it) }
            if (db != null) {
                for (i in k) {
                    db.grupaDao().insertGrupa(i)
                }
            }

            return@withContext k
        }
    }

    suspend fun getGrupeZaIstrazivanje(idIstrazivanja:Int):List<Grupa>{
        return withContext(Dispatchers.IO){
            var t=ApiAdapter.retrofit.getSveGrupe().toMutableList()
            t= t.filter { v-> v.idIstrazivanje==idIstrazivanja } as MutableList<Grupa>
            return@withContext t
        }
    }
    suspend fun getGrupeZaIstrazivanjeV2(idIstrazivanja:Int):List<Grupa>{
        return withContext(Dispatchers.IO){
            var t=ApiAdapter.retrofit.getSveGrupe().toMutableList()
            t.removeAll(ApiAdapter.retrofit.getGrupeUKojeJeUpisan(AccountRepository.getHash()))
            t= t.filter { v-> v.idIstrazivanje==idIstrazivanja } as MutableList<Grupa>
            return@withContext t
        }
    }

    suspend fun upisiUGrupu(idGrupa:Int):Boolean{
        return withContext(Dispatchers.IO){
            val up = ApiAdapter.retrofit.upisiStudentaNaGrupu(idGrupa,AccountRepository.acHash)
            return@withContext up.message.contains("je dodan")
        }
    }

    suspend fun getUpisaneGrupe():List<Grupa>{
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.getGrupeUKojeJeUpisan(AccountRepository.acHash)
        }

    }
}