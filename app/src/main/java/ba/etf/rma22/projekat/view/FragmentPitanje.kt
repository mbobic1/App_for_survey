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
import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.viewmodel.OdgovorViewModel
import java.util.*


class FragmentPitanje(var pitanje: Pitanje, var odg1 : Int, anketaTaken1: AnketaTaken) : Fragment() {
    lateinit var listView: ListView
    lateinit var textView: TextView
    var anketaTaken12 = anketaTaken1
    lateinit var button: Button
    var odg12345 : List<Odgovor> = emptyList()
    var odgovorPitanje : OdgovorViewModel = OdgovorViewModel()
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
        if(MainActivity.anketa.progres==null){
            MainActivity.anketa.progres=0
        }

        if(MainActivity.anketa.progres!!.compareTo(1.0)!=0 && !(MainActivity.anketa.datumKraj!=null&&MainActivity.anketa.datumKraj!!<trenutniDatum) && MainActivity.anketa.datumPocetak!!<=trenutniDatum) {
            listView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    MainActivity.brojiOdg++
                    val selectedItemText = parent.getItemAtPosition(position)
                    odgovorPitanje.dobijOdg(MainActivity.anketa.id, onSucces = ::onSuccess, onError = ::onError)
                    for (i2 in odg12345){
                        if(i2.id != position){
                            odgovorPitanje.dodajOdg(anketaTaken12,position, pitanje.id, onSucces = ::vrati)
                        }

                    }
                    (view as TextView).setTextColor(resources.getColor(R.color.pitanjeboja))

                }
            button.setOnClickListener {
                var odg = mutableListOf<Sacuvaj>()
                odg.addAll(MainActivity.sacuvajLista)
                MainActivity.sacuvaj.jelPritisnuto = false
                (activity as MainActivity).zaZaustavi()

            }
        }

        return view
    }

    fun  vrati(progres : Int){
        MainActivity.prog=progres
    }
    fun onError(){

    }
    fun onSuccess(odgovori : List<Odgovor>) {
        odg12345=odgovori
    }
}