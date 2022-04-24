package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.korisnik
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.pitanja
import ba.etf.rma22.projekat.data.pitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.PitanjeRepository.Companion.getAllPitanja

class PitanjaAnketaRepository {
    companion object{
        private var pitanja : List<Pitanje> = pitanja()
        init {

        }
        fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String) : List<Pitanje>{
            val vrati : MutableList<Pitanje> = arrayListOf()
            val pitanjaA1 : List<Pitanje> = PitanjeRepository.getAllPitanja()
            for( i in pitanjeAnketa()){
                for(j in pitanjaA1)
                if(i.anketa==nazivAnkete && i.naziv==j.naziv){
                    vrati.add(j)
                }
            }
            return vrati
        }
    }
}