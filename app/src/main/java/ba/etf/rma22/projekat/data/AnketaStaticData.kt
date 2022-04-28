package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import java.text.SimpleDateFormat
import java.util.*

fun datum(godina:Int, mjesec:Int, dan:Int):Date{
    return GregorianCalendar(godina, mjesec-1, dan).getTime()
}

fun ankete():List<Anketa>{
    return listOf(
       Anketa("Anketa 0", "Održavanje nastave online", datum(2021,4,4), datum(2022,5,28),
            datum(2021,4,4),5,"grupa1",0.2f),
       Anketa("Anketa 0", "Održavanje nastave online", datum(2021,4,4), datum(2021,5,14),
            datum(2021,4,4),5,"grupa2",0.4f),
       Anketa("Anketa 0", "Održavanje nastave online", datum(2021,4,4), datum(2021,5,14),
            datum(2021,4,4),5,"grupa3",0.6f),



       Anketa("Anekta 1","Provođenje slobodnog vremena", datum(2022,3,6), datum(2022,4,11),
            null,5,"grupa1",0.5f),
       Anketa("Anekta 1","Provođenje slobodnog vremena", datum(2022,3,6), datum(2022,4,11),
            null,5,"grupa2",0.1f),



       // ce se radit
       Anketa("Anketa 2","Važnost kvalitetnog obrazovanja", datum(2022,7,4), datum(2022,7,14),
               null,5,"grupa1",0.0f),
       Anketa("Anketa 2","Važnost kvalitetnog obrazovanja", datum(2022,7,4), datum(2022,7,14),
            null,5,"grupa2",0.0f),


       Anketa("Anekta 3","Preseljenje u druge zemlje", datum(2022,3,6), datum(2022,4,10),
        datum(2022,3,17),5,"grupa1",1f),
       Anketa("Anekta 3","Preseljenje u druge zemlje", datum(2022,3,6), datum(2022,4,10),
            datum(2022,3,17),5,"grupa2",0.9f),


       Anketa("Anekta 4","Položaj mladih na tržištu rada", datum(2022,3,6), datum(2022,4,15),
            datum(2022,3,17),5,"grupa1",1f),
       Anketa("Anekta 4","Položaj mladih na tržištu rada", datum(2022,3,6), datum(2022,4,15),
            datum(2022,3,17),5,"grupa2",1f)
    )
}