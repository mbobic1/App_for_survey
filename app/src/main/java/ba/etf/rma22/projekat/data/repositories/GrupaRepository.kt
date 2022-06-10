package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.grupe
import ba.etf.rma22.projekat.data.models.Grupa

class GrupaRepository   {
    companion object {
        //sluzi za upisivanje korisnika u grupu
        private  var grupe : List<Grupa> = grupe()
        init {

        }
       fun getGroupsByIstrazivanje(nazivIstrazivanja:String) : List<Grupa> {
           return emptyList()
         //  return grupe().filter { it.nazivIstrazivanja.equals(nazivIstrazivanja) }
       }

    }
}