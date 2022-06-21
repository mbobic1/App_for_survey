package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma22.projekat.MainActivity.Companion.korisnik
import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.grupe
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Korisnik
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object AnketaRepository {


        var context : Context?= null;

        fun sortiraj(lista: List<Anketa>): List<Anketa>{
            return lista.sortedBy { it.datumPocetak }
        }

        suspend fun getMyAnkete() : List<Anketa>{
            return withContext(Dispatchers.IO){
                val grupe = ApiAdapter.retrofit.getGrupeUKojeJeUpisan(AccountRepository.acHash)
                val listaAnketa = mutableListOf<Anketa>()
                for (grupa in grupe) {
                    listaAnketa.addAll(ApiAdapter.retrofit.getAnketaZaGrupu(grupa.id))
                }
                return@withContext listaAnketa
            }
        }

        suspend fun getAll() : List<Anketa>{
            return withContext(Dispatchers.IO){
                Log.i("usao12","usao")
                var t = mutableListOf<Anketa>()
                var i=1;
                val db = AccountRepository.context?.let { AppDatabase.getInstance(it) }
                while (true){
                    var k = ApiAdapter.retrofit.getOffsetAnekte(i)

                    if(k.size==0) break;
                    t.addAll(k)
                    i++
                }
                Log.i("usao12", "${t.size}", )

                if (db != null) {
                    for(bl in t) {
                        Log.i("usao12","upisujem")
                        db.anketaDao().insertAnketa(bl)
                    }
                }
                else{
                    Log.i("usao12","else")
                }
                Log.i("usao12","t)${t.size}")
                return@withContext t
            }
        }

        suspend fun getAll(offset : Int) : List<Anketa>{
            return withContext(Dispatchers.IO){
                val sveAnkete = ApiAdapter.retrofit.getOffsetAnekte(offset)
                return@withContext sveAnkete
            }
        }


        suspend fun getById(id:Int):Anketa{
            return withContext(Dispatchers.IO){
                return@withContext ApiAdapter.retrofit.getAnketa(id)
            }
        }
        suspend fun getAnketeZaGrupu23(id:Int):List<Grupa>{
            return withContext(Dispatchers.IO){
                return@withContext ApiAdapter.retrofit.getGrupeDostupnaAnketa(id)
            }
        }

        suspend fun getUpisane():List<Anketa> {
            return withContext(Dispatchers.IO) {
                val grupe = ApiAdapter.retrofit.getGrupeUKojeJeUpisan(AccountRepository.acHash)
                val listaAnketa = mutableListOf<Anketa>()
                for (grupa in grupe) {
                    listaAnketa.addAll(ApiAdapter.retrofit.getAnketaZaGrupu(grupa.id))
                }
                return@withContext listaAnketa
            }
        }
        suspend fun getDone() : List<Anketa>{
            return withContext(Dispatchers.IO){
                return@withContext getMyAnkete().filter { it.progres!!.compareTo(1.0)==0 }
            }
        }
        suspend fun getFuture() : List<Anketa>{
            val trenutniDatum : Date = Calendar.getInstance().run{ time };
            return withContext(Dispatchers.IO){
                return@withContext getMyAnkete().filter { anketa ->anketa.datumPocetak!!>trenutniDatum }
            }
        }
        suspend fun getNotTaken() : List<Anketa>{
            val trenutniDatum : Date = Calendar.getInstance().run{ time };
            return withContext(Dispatchers.IO){
                return@withContext getMyAnkete().filter { anketa -> trenutniDatum>anketa.datumKraj && anketa.progres!!.compareTo(1.0)!=0}
            }
        }

}