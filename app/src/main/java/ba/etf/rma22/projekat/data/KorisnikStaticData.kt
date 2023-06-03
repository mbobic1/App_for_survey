package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Korisnik
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

fun korisnik() : Korisnik{
    return (
        Korisnik(mutableListOf(ankete()[0]),mutableListOf(grupe()[0]),mutableListOf(istrazivanja()[0]), 1 )
    )
}


