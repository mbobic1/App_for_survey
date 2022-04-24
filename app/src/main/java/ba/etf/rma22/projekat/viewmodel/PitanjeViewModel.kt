package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.PitanjeRepository

class PitanjeViewModel {

    fun getVelicinu() : Int{
        return PitanjeRepository.getVelicinu();
    }

    fun getAllPitanja() : List<Pitanje>{
        return PitanjeRepository.getAllPitanja()
    }
}