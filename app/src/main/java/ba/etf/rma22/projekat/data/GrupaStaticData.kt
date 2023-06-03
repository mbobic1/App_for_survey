package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Grupa
import java.util.*

fun grupe():List<Grupa>{
    return listOf(
        Grupa(1,"grupa1", 0),
        Grupa(1,"grupa2", 0),
        Grupa(1,"grupa3", 0),


        Grupa(2,"grupa1", 1),
        Grupa(2,"grupa2", 1),


        Grupa(3,"grupa1", 2),
        Grupa(3,"grupa2",2),

        Grupa(4,"grupa1", 3),
        Grupa(4,"grupa2", 3),

        Grupa(5,"grupa1", 4),
        Grupa(5,"grupa2", 4)
    )
}