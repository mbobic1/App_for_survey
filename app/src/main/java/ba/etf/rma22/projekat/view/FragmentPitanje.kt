package ba.etf.rma22.projekat.view


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.Sacuvaj
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import java.util.*


class FragmentPitanje(var pitanje: Pitanje) : Fragment() {
    lateinit var listView: ListView
    lateinit var textView: TextView
    lateinit var button: Button
    val trenutniDatum : Date = Calendar.getInstance().run{ time };

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.pitanje, container, false)
        textView=view.findViewById(R.id.tekstPitanja)
        listView=view.findViewById(R.id.odgovoriLista)
        button=view.findViewById(R.id.dugmeZaustavi)
        textView.setText(pitanje.tekstPitanja)
        val list = pitanje.opcije.toMutableList()
        val adapter = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_list_item_1,list
        )
        listView.adapter=adapter
        if(MainActivity.sacuvaj.anketa.progres.compareTo(1.0)!=0 && MainActivity.sacuvaj.anketa.datumKraj!=null&&MainActivity.sacuvaj.anketa.datumKraj>trenutniDatum) {
            listView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
                (view as TextView).setTextColor(resources.getColor(R.color.pitanjeboja))
                var odg = mutableListOf<Sacuvaj>()
                odg.addAll(MainActivity.sacuvajLista)
                var odg1 = odg.map { t -> t.anketa }
                MainActivity.sacuvaj.odovori[odg1.indexOf(MainActivity.anketa)].add(position)
            })

            button.setOnClickListener {
                var odg = mutableListOf<Sacuvaj>()
                odg.addAll(MainActivity.sacuvajLista)
                var odg1 = odg.map { t -> t.anketa }
                MainActivity.sacuvaj.jelPritisnuto = false
                MainActivity.sacuvajLista[odg1.indexOf(MainActivity.anketa)] = MainActivity.sacuvaj
                (activity as MainActivity).zaZaustavi()

            }
        }


        return view
    }
}