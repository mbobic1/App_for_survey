package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import kotlinx.coroutines.*

class AccountViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.IO)

    fun upisiUBazuPodataka(onSuccess: (jel: Boolean) -> Unit, onError: () -> Unit){
        scope.launch {
            println("Usao u bazu u viemodel")
            val jel = AccountRepository.upisiAccUBazu()
            withContext(Dispatchers.Main) {
                when (jel) {
                    is Boolean -> onSuccess.invoke(jel)
                    else -> onError.invoke()
                }
            }
        }
    }

    fun postaviHash(hash : String){
        scope.launch {
            val jel = AccountRepository.postaviHash(hash)
        }
    }
}