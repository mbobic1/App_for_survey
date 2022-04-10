package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Grupa
import java.util.*

fun grupe():List<Grupa>{
    return listOf(
        Grupa("grupa1", istrazivanja()[0].naziv),
        Grupa("grupa2", istrazivanja()[0].naziv),
        Grupa("grupa3", istrazivanja()[0].naziv),


        Grupa("grupa1", istrazivanja()[1].naziv),
        Grupa("grupa2", istrazivanja()[1].naziv),


        Grupa("grupa1", istrazivanja()[2].naziv),
        Grupa("grupa2", istrazivanja()[2].naziv),

        Grupa("grupa1", istrazivanja()[3].naziv),
        Grupa("grupa2", istrazivanja()[3].naziv),

        Grupa("grupa1", istrazivanja()[4].naziv),
        Grupa("grupa2", istrazivanja()[4].naziv)
    )
}