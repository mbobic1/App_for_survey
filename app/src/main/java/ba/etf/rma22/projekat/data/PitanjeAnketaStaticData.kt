package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.PitanjeAnketa

fun pitanjeAnketa() : List<PitanjeAnketa>{
    return listOf(
        PitanjeAnketa("pitanje 1","Anketa 0"),
        PitanjeAnketa("pitanje 2","Anketa 1"),
        PitanjeAnketa("pitanje 3","Anketa 2")

    )

}