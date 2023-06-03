package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

class IstrazivanjeViewModel {

    fun getAll() : List<Istrazivanje>{                                      //- lista svih istraživanja
        return IstrazivanjeRepository.getAll();
    }
    fun getUpisani() : List<Istrazivanje> {
        //- lista istraživanja na kojima je korisnik upisan
        var lista= ankete();
        return IstrazivanjeRepository.getUpisani()

    }
    fun getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje> {          //- lista istraživanja na godini
        return IstrazivanjeRepository.getIstrazivanjeByGodina(godina);
    }
}