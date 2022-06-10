package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {

    suspend fun zapocniAnketu(idAnkete:Int): AnketaTaken {
        return withContext(Dispatchers.IO){
            return@withContext ApiAdapter.retrofit.zapocniRjesavanjeAnkete(AccountRepository.acHash, idAnkete)
        }
    }

    suspend fun getPoceteAnkete():List<AnketaTaken>?{
        return withContext(Dispatchers.IO){
            var pokusaji = ApiAdapter.retrofit.getPokusajeZaStudenta(AccountRepository.getHash())
            if(pokusaji.size==0){
                return@withContext null
            }
            return@withContext pokusaji;
        }

    }
}