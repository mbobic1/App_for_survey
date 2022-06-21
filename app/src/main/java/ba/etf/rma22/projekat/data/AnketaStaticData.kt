package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import java.text.SimpleDateFormat
import java.util.*

fun datum(godina:Int, mjesec:Int, dan:Int):Date{
    return GregorianCalendar(godina, mjesec-1, dan).getTime()
}

fun ankete():List<Anketa>{
    /*return listOf(
       Anketa(1,"Anketa 0", "Održavanje nastave online", datum(2021,4,4), datum(2022,5,28),
            datum(2021,4,4),5,"grupa1",0.2f, "prva"),
       Anketa(1,"Anketa 0", "Održavanje nastave online", datum(2021,4,4), datum(2021,5,14),
            datum(2021,4,4),5,"grupa2",0.4f, "prv"),
       Anketa(1,"Anketa 0", "Održavanje nastave online", datum(2021,4,4), datum(2021,5,14),
            datum(2021,4,4),5,"grupa3",0.6f, "prva"),



       Anketa(2,"Anekta 1","Provođenje slobodnog vremena", datum(2022,3,6), datum(2022,4,11),
            null,5,"grupa1",0.5f,"druga"),
       Anketa(2,"Anekta 1","Provođenje slobodnog vremena", datum(2022,3,6), datum(2022,4,11),
            null,5,"grupa2",0.1f, "druga"),



       // ce se radit
       Anketa(3,"Anketa 2","Važnost kvalitetnog obrazovanja", datum(2022,7,4), datum(2022,7,14),
               null,5,"grupa1",0.0f, "treca"),
       Anketa(3,"Anketa 2","Važnost kvalitetnog obrazovanja", datum(2022,7,4), datum(2022,7,14),
            null,5,"grupa2",0.0f, "treca"),


       Anketa(4,"Anekta 3","Preseljenje u druge zemlje", datum(2022,3,6), datum(2022,4,10),
        datum(2022,3,17),5,"grupa1",1f, "cetvrta"),
       Anketa(4,"Anekta 3","Preseljenje u druge zemlje", datum(2022,3,6), datum(2022,4,10),
            datum(2022,3,17),5,"grupa2",0.9f, "cetvrta"),


       Anketa(5,"Anekta 4","Položaj mladih na tržištu rada", datum(2022,3,6), datum(2022,4,15),
            datum(2022,3,17),5,"grupa1",1f, "peta"),
       Anketa(5,"Anekta 4","Položaj mladih na tržištu rada", datum(2022,3,6), datum(2022,4,15),
            datum(2022,3,17),5,"grupa2",1f, "peta")
    )*/
    return emptyList()
}