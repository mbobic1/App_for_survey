package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.MainActivity.Companion.korisnik
import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Korisnik
import java.util.*

class AnketaRepository {
    companion object {
        init {

        }
        fun sortiraj(lista: List<Anketa>): List<Anketa>{
            return lista.sortedBy { it.datumPocetak }
        }

        fun getMyAnkete() : List<Anketa>{
            return KorisnikRepository.getAnketa1()
        }

        fun getAll() : List<Anketa>{
            return ankete()
        }

        fun getDone() : List<Anketa>{
            val trenutniDatum : Date = Calendar.getInstance().run{ time };
            return getMyAnkete().filter { anketa -> anketa.progres.compareTo(1.0)==0 }
        }
        fun getFuture() : List<Anketa>{
            val trenutniDatum : Date = Calendar.getInstance().run{ time };
            return getMyAnkete().filter { anketa ->anketa.datumPocetak>trenutniDatum }
        }
        fun getNotTaken() : List<Anketa>{
            val trenutniDatum : Date = Calendar.getInstance().run{ time };
            return getMyAnkete().filter { anketa -> trenutniDatum>anketa.datumKraj && anketa.progres.compareTo(1.0)!=0}
        }

    }


}