package ba.etf.rma22.projekat.data


import ba.etf.rma22.projekat.data.models.Istrazivanje
import java.util.*

fun datum(godina:Int, mjesec:Int, dan:Int): Date {
    return GregorianCalendar(godina, mjesec-1, dan).getTime()
}

fun istrazivanja():List<Istrazivanje>{
    return listOf(
        Istrazivanje(1,"Održavanje nastave online", 1),
        Istrazivanje(2,"Važnost kvalitetnog obrazovanja", 2),
        Istrazivanje(3,"Provođenje slobodnog vremena", 3),
        Istrazivanje(4,"Preseljenje u druge zemlje", 4),
        Istrazivanje(5,"Položaj mladih na tržištu rada", 5),

    )
}