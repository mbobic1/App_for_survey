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
import ba.etf.rma22.projekat.data.models.Pitanje


class FragmentPitanje(var pitanje : Pitanje) : Fragment() {
    lateinit var listView: ListView
    lateinit var textView: TextView
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.pitanje, container, false)
        textView=view.findViewById(R.id.tekstPitanja)
        listView=view.findViewById(R.id.odgovoriLista)
        button=view.findViewById(R.id.dugmeZaustavi)
        textView.setText(pitanje.naziv)

        val list = pitanje.opcije.toMutableList();
        val adapter = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_list_item_1,list
        )
        listView.adapter=adapter
        listView.setOnItemClickListener(AdapterView.OnItemClickListener{
            parent,view, position, id->
            (view as TextView).setTextColor(resources.getColor(R.color.pitanjeboja))
        })

        button.setOnClickListener{

        }


        return view
    }
}