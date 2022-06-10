package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Pitanje
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
}