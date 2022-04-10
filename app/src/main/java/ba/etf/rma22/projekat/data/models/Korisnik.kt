package ba.etf.rma22.projekat.data.models

data class Korisnik(
        var listaAnketa: MutableList<Anketa>,
        var listaGrupa: MutableList<Grupa>,
        var listaIstrazivanje: MutableList<Istrazivanje>,
        var godinaStudiranja: Int =0
        )
