package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.grupe
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.GrupaRepository

class GrupaViewModel {
    fun getGroupsByIstrazivanje(nazivIstrazivanja:String) : List<Grupa>? {
        return emptyList();
        //return GrupaRepository.getGroupsByIstrazivanje(nazivIstrazivanja)
    }
}

