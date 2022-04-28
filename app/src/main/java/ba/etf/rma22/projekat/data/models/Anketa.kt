package ba.etf.rma22.projekat.data.models

import java.util.*

data class Anketa(
    var naziv: String,
    var nazivIstrazivanja: String,
    var datumPocetak: Date,
    var datumKraj: Date,
    var datumRada: Date?,
    var trajanje: Int,
    var nazivGrupe: String,
    var progres: Float
    )
