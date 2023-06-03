package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.korisnik
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Korisnik
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel

class KorisnikRepository {
    companion object {
        init {

        }
        private var anketaViewHModel : AnketaViewModel = AnketaViewModel()
        private var korisnik = korisnik()

        fun getKorisnik() : Korisnik {
            return korisnik
        }

        fun getAnketa1(): List<Anketa> {
            return korisnik.listaAnketa
        }
        fun getIstrazivanje1() : List<Istrazivanje>{
            return korisnik.listaIstrazivanje
        }
        fun getGroup1() : List<Grupa>{
            return korisnik.listaGrupa
        }


        fun upisiKorisnika(grupa: String, istrazivanje : String ,godina: Int){
            korisnik.godinaStudiranja=godina
            //korisnik.listaIstrazivanje.add( Istrazivanje(istrazivanje,godina))
            //korisnik.listaGrupa.add( Grupa(grupa, istrazivanje))
            //Istrazivanje(istrazivanje,godina)
            //Grupa(grupa, istrazivanje)
            /*for( i in anketaViewHModel.getAll()){
                if(i.nazivIstrazivanja==istrazivanje && i.nazivGrupe==grupa){
                    korisnik.listaAnketa.add(i);

                }
            }*/

        }
    }
}