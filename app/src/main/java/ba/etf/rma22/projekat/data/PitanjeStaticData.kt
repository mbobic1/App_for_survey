package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Pitanje

fun pitanja() : List<Pitanje>{
    return listOf(
        Pitanje("pitanje 1", "Sta je ovo", listOf("kuca", "auto", "zgrada")),
        Pitanje("pitanje 2", "Sta radis", listOf("lezim", "spavam", "odmaram")),
        Pitanje("pitanje 3", "Gdje si", listOf("kuca", "plaza", "skola"))
    )
}