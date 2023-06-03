package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.ApiAdapter
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import kotlinx.coroutines.*

class PitanjeAnketaViewModel {
    fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String) : List<Pitanje>{
        return PitanjeAnketaRepository.getPitanja(nazivAnkete, nazivIstrazivanja)
    }
    var scope= CoroutineScope(Job() +Dispatchers.IO)
    fun getPitanja(id:Int, onSucces: (grupe: List<Pitanje>) -> Unit, onError: () -> Unit)
    {
        scope.launch {
            var t=ApiAdapter.retrofit.getPitanja(id)

            withContext(Dispatchers.Main){
                when (t) {
                    is List<Pitanje> -> onSucces.invoke(t)
                    else -> onError.invoke()
                }
            }
        }
    }
    fun getPitanjaV2(idAnkete:Int, onSucces: (grupe: List<Pitanje>, odgovori : List<Odgovor>, anketaTaken : AnketaTaken) -> Unit, onError: () -> Unit)
    {
        scope.launch {
            var t=ApiAdapter.retrofit.getPitanja(idAnkete)
            var odg1 = ApiAdapter.retrofit.getPokusajeZaStudenta(AccountRepository.getHash())
            var anketaTaken : AnketaTaken
            if(!odg1.map { it->it.AnketumId }.contains(idAnkete)){
                anketaTaken=ApiAdapter.retrofit.zapocniRjesavanjeAnkete(AccountRepository.getHash(),idAnkete)
            }
            else{
                anketaTaken=odg1.last{
                    it->it.AnketumId==idAnkete
                }
            }
            var vratiOdg = ApiAdapter.retrofit.getOdgovoriAnkete(anketaTaken.id,AccountRepository.getHash())
            withContext(Dispatchers.Main){
                when (t) {
                    is List<Pitanje> -> onSucces.invoke(t, vratiOdg, anketaTaken)
                    else -> onError.invoke()
                }
            }
        }
    }
}
