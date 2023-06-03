package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository
import kotlinx.coroutines.*

class TakeAnketaViewModel {
    var scope = CoroutineScope(Job() + Dispatchers.IO)

    fun zapocniAnketu(idAnketa : Int, onSucces : (istrazivanja : AnketaTaken)->Unit, onError:() ->Unit){
        scope.launch {
            val istrazivanja = TakeAnketaRepository.zapocniAnketu(idAnketa)
            withContext(Dispatchers.Main){
                when (istrazivanja) {
                    is AnketaTaken -> onSucces?.invoke(istrazivanja)
                    else -> onError.invoke()
                }
            }
        }
    }

    fun getPoceteAnkete(onSucces : (istrazivanja : List<AnketaTaken>)->Unit, onError:() ->Unit){
        scope.launch {
            val istrazivanja = TakeAnketaRepository.getPoceteAnkete()
            withContext(Dispatchers.Main){
                when (istrazivanja) {
                    is List<AnketaTaken> -> onSucces?.invoke(istrazivanja)
                    else -> onError.invoke()
                }
            }
        }
    }


}