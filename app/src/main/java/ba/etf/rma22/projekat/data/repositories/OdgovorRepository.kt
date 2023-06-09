package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorPitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

object OdgovorRepository {
    var context : Context?= null;

    suspend fun getOdgovoriAnketa(idAnkete:Int):List<Odgovor>{
        return withContext(Dispatchers.IO){
            val anketaP = TakeAnketaRepository.getPoceteAnkete()
            val mojeAnkete = AnketaRepository.getMyAnkete()
            if(mojeAnkete.find { it.id == idAnkete } == null ||  anketaP!!.find { it.AnketumId==idAnkete } == null){
                return@withContext mutableListOf<Odgovor>()
            }
            val ank = anketaP!!.find { it.AnketumId == idAnkete }
            var yu=ApiAdapter.retrofit.getOdgovoriAnkete( ank!!.id,AccountRepository.getHash())
            val db = AccountRepository.context?.let { AppDatabase.getInstance(it) }
            for(l in yu)
            db!!.odgovorDao().insertOdgovor(l)
            return@withContext  yu
        }

    }

   suspend fun postaviOdgovorAnketa(idAnketaTaken:Int,idPitanje:Int,odgovor:Int):Int{
       return withContext(Dispatchers.IO){
           try {
               var vrati : Int = 0;
               var ank = TakeAnketaRepository.getPoceteAnkete()!!.find { it.id == idAnketaTaken }
               var pitanje = PitanjeAnketaRepository.getPitanja(ank!!.AnketumId)
               var odgovori = pitanje.indexOfFirst { it -> it.id == idPitanje } + 1
               var prog = ((odgovori.toFloat()/pitanje.size.toFloat())*100f).toInt()
               for (i in 100 downTo 0 step 20){
                   if(i-prog<=10){
                       vrati=i;
                       break;
                   }
               }
               ApiAdapter.retrofit.dodajOdgovorZaPokusaj(
                   AccountRepository.getHash(),
                   idAnketaTaken,
                   OdgovorPitanje(odgovor,idPitanje,vrati)
               )
               getOdgovoriAnketa(ank!!.AnketumId)
               return@withContext vrati
           }catch (e : Exception){
               return@withContext -1
           }
       }
   }
}