package ba.etf.rma22.projekat.data


import ba.etf.rma22.projekat.data.models.Istrazivanje
import java.util.*

fun datum(godina:Int, mjesec:Int, dan:Int): Date {
    return GregorianCalendar(godina, mjesec-1, dan).getTime()
}

fun istrazivanja():List<Istrazivanje>{
    return listOf(
        Istrazivanje("Održavanje nastave online", 1),
        Istrazivanje("Važnost kvalitetnog obrazovanja", 2),
        Istrazivanje("Provođenje slobodnog vremena", 3),
        Istrazivanje("Preseljenje u druge zemlje", 4),
        Istrazivanje("Položaj mladih na tržištu rada", 5),

    )
}