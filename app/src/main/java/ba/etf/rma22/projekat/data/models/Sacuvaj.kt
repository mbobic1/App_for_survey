package ba.etf.rma22.projekat.data.models

data class Sacuvaj(
    var anketa: Anketa,
    var odovori : MutableList<MutableList<Int>>,
    var progres1 : Int,
    var jelPritisnuto : Boolean

)
