package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.MainActivity.Companion.korisnik
import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje

class IstrazivanjeRepository {
    companion object {
        private var istrazivanja:List<Istrazivanje> = istrazivanja()
        init {

        }

        fun getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje> {          //- lista istraživanja na godini
                return istrazivanja().filter { it.godina.equals(godina) }
        }
        fun getAll() : List<Istrazivanje>{                                      //- lista svih istraživanja
            return istrazivanja()
        }
        fun getUpisani() : List<Istrazivanje> {
        //- lista istraživanja na kojima je korisnik upisan
            var lista= ankete();
            return KorisnikRepository.getIstrazivanje1()

        }

    }
}