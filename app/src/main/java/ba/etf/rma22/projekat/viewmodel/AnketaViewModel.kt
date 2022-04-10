package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository

class AnketaViewModel {

    fun sortiraj(lista: List<Anketa>): List<Anketa>{
        return lista.sortedBy { it.datumPocetak }
    }

    fun getDone(): List<Anketa>{
        return sortiraj(AnketaRepository.getDone())
    }
    fun getAll(): List<Anketa>{
        return sortiraj(AnketaRepository.getAll())
    }
    fun getFuture(): List<Anketa>{
        return sortiraj(AnketaRepository.getFuture())
    }
    fun getNotTaken(): List<Anketa>{
        return sortiraj(AnketaRepository.getNotTaken())
    }
    fun getMyAnkete() : List<Anketa>{
        return sortiraj(AnketaRepository.getMyAnkete())
    }

}