package ba.etf.rma22.projekat.viewmodel

import android.content.Context
import android.provider.Settings
import ba.etf.rma22.projekat.data.grupe
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Message
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.ApiAdapter
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.*

class IstrazivanjeIGrupaViewModel {

    var scope = CoroutineScope(Job() + Dispatchers.IO)

    fun getIstrazivanje(onSucces : (istrazivanja : List<Istrazivanje>)->Unit, onError:() ->Unit){
        scope.launch {
            val istrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanja()
            withContext(Dispatchers.Main){
                when (istrazivanja) {
                    is List<Istrazivanje> -> onSucces?.invoke(istrazivanja)
                    else -> onError.invoke()
                }
            }
        }
    }
fun gon(id:Int, onSucces: (grupe: String) -> Unit, onError: () -> Unit){
    scope.launch {
        val grupe = AnketaRepository.getAnketeZaGrupu23(id)
        var t=ApiAdapter.retrofit.getIstrazivanje(grupe[0].idIstrazivanje)
        withContext(Dispatchers.Main){
            when (grupe) {
                is List<Grupa> -> onSucces.invoke(t.naziv)
                else -> onError.invoke()
            }
        }

}
    }
    fun getGrupeZaIstrazivanje(idIstrazivanja : Int, onSucces: (grupe: List<Grupa>) -> Unit, onError: () -> Unit){
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(idIstrazivanja)
            withContext(Dispatchers.Main){
                when (grupe) {
                    is List<Grupa> -> onSucces.invoke(grupe)
                    else -> onError.invoke()
                }
            }
        }

    }
    fun getGrupeZaIstrazivanjeV2(idIstrazivanja : Int, onSucces: (grupe: List<Grupa>, context : Context) -> Unit, onError: () -> Unit, context: Context){
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanjeV2(idIstrazivanja)
            grupe.toMutableList().removeAll(IstrazivanjeIGrupaRepository.getUpisaneGrupe())
            withContext(Dispatchers.Main){
                when (grupe) {
                    is List<Grupa> -> onSucces.invoke(grupe,context)
                    else -> onError.invoke()
                }
            }
        }

    }

    fun getIstrazivanjeByGodina(godina : Int, onSucces: (Istrazivanje1: List<Istrazivanje>, context : Context) -> Unit, onError: () -> Unit, context1: Context){
        scope.launch {
            val istrazivanja1 = IstrazivanjeIGrupaRepository.getIstrazivanja().filter { it.godina==godina}
            val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            val vrati = mutableListOf<Istrazivanje>()
            var t=0
            for(i in istrazivanja1){
                t=0
                for(j in grupe){
                    if(j.idIstrazivanje==i.id){
                        t=1
                        break
                    }

                }
                if(t==0)
                vrati.add(i)
            }
            withContext(Dispatchers.Main){
                when(vrati){
                    is List<Istrazivanje> -> onSucces.invoke(vrati, context1)
                    else -> onError.invoke()
                }
            }
        }

    }

    fun getGrupe(onSucces : (grupe : List<Grupa>)->Unit, onError: () -> Unit){
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getGrupe()
            withContext(Dispatchers.Main){
                when (grupe) {
                    is List<Grupa> -> onSucces.invoke(grupe)
                    else -> onError.invoke()
                }
            }
        }
    }

    fun upisiUGrupu(idGrupe:Int, onSuccess:()-> Unit, onError:()->Unit){
        scope.launch{
            var upis = IstrazivanjeIGrupaRepository.upisiUGrupu(idGrupe)
            withContext(Dispatchers.Main){
                when (upis) {
                    is Boolean->onSuccess?.invoke()
                    else -> onError?.invoke()
                }
            }
        }
    }
}