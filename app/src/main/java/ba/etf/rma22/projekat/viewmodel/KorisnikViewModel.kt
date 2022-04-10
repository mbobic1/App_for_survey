package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.korisnik
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Korisnik
import ba.etf.rma22.projekat.data.repositories.KorisnikRepository

class KorisnikViewModel {
       fun getKorisnik() : Korisnik {
        return KorisnikRepository.getKorisnik()
    }

    fun getAnketa1(): List<Anketa> {
        return KorisnikRepository.getAnketa1()
    }
    fun getIstrazivanje1() : List<Istrazivanje>{
        return KorisnikRepository.getIstrazivanje1()
    }
    fun getGroup1() : List<Grupa>{
        return KorisnikRepository.getGroup1()
    }


    fun upisiKorisnika(grupa: String, istrazivanje : String ,godina: Int){
        return KorisnikRepository.upisiKorisnika(grupa, istrazivanje, godina)
    }
}