package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.pitanja

class PitanjeRepository {
    companion object{
        init {

        }

        fun getVelicinu() : Int{
            return pitanja().size
        }
        fun getAllPitanja() : List<Pitanje>{
            return pitanja()
        }
    }
}