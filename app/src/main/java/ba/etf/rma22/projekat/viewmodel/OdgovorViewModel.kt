package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorPitanje
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.ApiAdapter
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import kotlinx.coroutines.*

class OdgovorViewModel {
    var scope = CoroutineScope(Job() + Dispatchers.IO)

    fun dodajOdg(anketaTaken: AnketaTaken, odgovor1 : Int, pit : Int, onSucces: (prof : Int) -> Unit) {
        scope.launch {
            var pro =0;
            var pitko = ApiAdapter.retrofit.getPitanja(anketaTaken.AnketumId)
            var k = pitko.indexOfLast { it-> it.id == anketaTaken.AnketumId }
            pro = ((k+1)/pitko.size)*100
            var vrati =0;
            for (i in 100 downTo 0 step 20){
                if (i-pro<=10){
                    vrati=i
                    break
                }
            }
            println("Progres ankete je ${vrati}")
            ApiAdapter.retrofit.dodajOdgovorZaPokusaj(AccountRepository.getHash(),anketaTaken.id, OdgovorPitanje(odgovor1,pit, vrati))        }
    }

    fun dobijOdg(id: Int, onSucces: (grupe: List<Odgovor>) -> Unit, onError: () -> Unit) {
        scope.launch {
            var t = ApiAdapter.retrofit.getOdgovoriAnkete(id, AccountRepository.getHash())
            withContext(Dispatchers.Main) {
                when (t) {
                    is List<Odgovor> -> onSucces.invoke(t)
                    else -> onError.invoke()
                }
            }

        }
    }
}