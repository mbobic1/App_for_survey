package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Sacuvaj
import kotlin.math.roundToInt

class FragmentPredaj : Fragment() {
     private lateinit var textView: TextView
     private lateinit var button : Button
     private lateinit var anketaAdapter: AnketaListAdapter


     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
          var view = inflater.inflate(R.layout.predaj, container, false)
          textView = view.findViewById(R.id.progresTekst)
          button = view.findViewById(R.id.dugmePredaj)


         button.setOnClickListener{ ;

             MainActivity.predaj = 1
              var odg = mutableListOf<Sacuvaj>()
              odg.addAll(MainActivity.sacuvajLista)
              var odg1=odg.map{ t-> t.anketa}
              MainActivity.sacuvaj.jelPritisnuto=true
              MainActivity.sacuvajLista[odg1.indexOf(MainActivity.anketa)]=MainActivity.sacuvaj
              (activity as MainActivity).zaPredaj()
          }
          return view
     }

     fun postavi() {
         var pit = MainActivity.sacuvaj.odovori.size
          var odg = mutableListOf<List<Int>>()
         odg.addAll(MainActivity.sacuvaj.odovori)
          odg.removeIf{t -> t.size ==0 }
          var prog = (kotlin.math.round(((odg.size.toFloat() / pit.toFloat())/0.2))*20).roundToInt()
         MainActivity.sacuvaj.progres1=prog
         textView.setText("${MainActivity.sacuvaj.progres1}%")
         MainActivity.sacuvajLista.add(MainActivity.sacuvaj)
     }
}

