package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa

class FragmentPredaj : Fragment() {
     private lateinit var textView: TextView
     private lateinit var button : Button


     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
          var view = inflater.inflate(R.layout.predaj, container, false)
          textView = view.findViewById(R.id.progresTekst)
          button = view.findViewById(R.id.dugmePredaj)

          //val progres1 = anketa.progres.toString()
          textView.setText(" asjdfoasjkfsa ")

          button.setOnClickListener{
               MainActivity.predaj = 1
               (activity as MainActivity).zaPredaj()
          }
          return view
     }
}

