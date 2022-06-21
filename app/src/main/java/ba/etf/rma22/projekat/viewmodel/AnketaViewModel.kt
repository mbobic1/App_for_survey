package ba.etf.rma22.projekat.viewmodel

import android.provider.Settings
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.ApiAdapter
import ba.etf.rma22.projekat.view.AnketaListAdapter
import kotlinx.coroutines.*
import java.util.*

class AnketaViewModel {

    var scope = CoroutineScope(Job() +Dispatchers.IO)

    fun getAnketaById(idAnkete : Int, onSuccess: (ankete: Anketa) -> Unit, onError: () -> Unit){
        scope.launch {
            val ankete = AnketaRepository.getById(idAnkete)
        withContext(Dispatchers.Main){
                when (ankete) {
                    is Anketa -> onSuccess?.invoke(ankete)
                    else -> onError.invoke()
                }
            }
        }
    }

    fun getDone(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit){
        scope.launch {
            val ankete = AnketaRepository.getDone()
            withContext(Dispatchers.Main){
                when (ankete) {
                    is List<Anketa> -> onSuccess.invoke(ankete)
                    else -> onError.invoke()
                }
            }
        }

    }
    fun getAll(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit){
        scope.launch {
            val ankete = AnketaRepository.getAll()
            withContext(Dispatchers.Main){
                when (ankete) {
                    is List<Anketa> -> onSuccess.invoke(ankete)
                    else -> onError.invoke()
                }
            }
        }
    }

    fun getFuture(onSuccess : (buduceAnkete : List<Anketa>)->  Unit, onError:() -> Unit){
        scope.launch {
            val buduceAnkete = AnketaRepository.getFuture()
            withContext(Dispatchers.Main){
                when (buduceAnkete) {
                    is List<Anketa> -> onSuccess.invoke(buduceAnkete)
                    else -> onError.invoke()
                }
            }
        }
    }

    fun getProsle(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> kotlin.Unit){
        scope.launch {
            val trenutniDatum : Date = Calendar.getInstance().run{ time };
            val ankete = AnketaRepository.getMyAnkete().filter { it->it.datumKraj!=null && it.datumKraj!!< trenutniDatum}
            withContext(Dispatchers.Main){
                when(ankete){
                    is List<Anketa> -> onSuccess.invoke(ankete)
                    else -> onError.invoke()
                }
            }
        }

    }
    fun getNotTaken(onSuccess: (ankete: List<Anketa>) -> Unit, onError: ()-> Unit){
        scope.launch {
            val ankete = AnketaRepository.getNotTaken()
            withContext(Dispatchers.Main){
                when (ankete) {
                    is List<Anketa> -> onSuccess.invoke(ankete)
                    else -> onError.invoke()
                }
            }
        }
    }


    fun getMyAnkete(onSuccess: (ankete : List<Anketa>) -> Unit, onError: () -> Unit){
        scope.launch {
            val ankete = AnketaRepository.getMyAnkete()
            withContext(Dispatchers.Main){
                when (ankete) {
                    is List<Anketa> -> onSuccess.invoke(ankete)
                    else -> onError.invoke()
                }
            }
        }
    }



    fun getMyAnketeV2(onSuccess: (ankete : List<Anketa>, trenutniDatum: Date, holder: AnketaListAdapter.AnketaViewHolder,
                                  position: Int, ankete1 : Anketa) -> Unit, onError: () -> Unit, trenutniDatum1: Date, holder1: AnketaListAdapter.AnketaViewHolder, position1: Int,ankete1: Anketa){
        scope.launch {
            val ankete = AnketaRepository.getMyAnkete()
            withContext(Dispatchers.Main){
                when (ankete) {
                    is List<Anketa> -> onSuccess.invoke(
                        ankete,
                        trenutniDatum1,
                        holder1,
                        position1,
                        ankete1
                    )
                    else -> onError.invoke()
                }
            }
        }
    }
}