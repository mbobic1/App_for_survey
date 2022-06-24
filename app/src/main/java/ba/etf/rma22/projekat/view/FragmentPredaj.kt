package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.Sacuvaj
import ba.etf.rma22.projekat.data.pitanja
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeRepository
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeIGrupaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
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
         var ispisi: Float
         var ispisi1 : Int
         if(MainActivity.brojiOdg<=MainActivity.uzmiPitanjaBroj)
            ispisi= MainActivity.brojiOdg.toFloat()/MainActivity.uzmiPitanjaBroj.toFloat()
         else ispisi = MainActivity.uzmiPitanjaBroj.toFloat()/MainActivity.brojiOdg.toFloat()
         ispisi1 = (ispisi*100).toInt()
         for(i in 100 downTo 10 step 20){
           if(i-ispisi1<=10){
               ispisi1=i
               break
           }
         }
         textView.setText("${ispisi1}%")

         button.setOnClickListener{ ;
                IstrazivanjeIGrupaViewModel().gon(MainActivity.anketa.id, onSucces = ::moj, onError = ::onError)
          }
          return view
     }
fun moj(a:String)
{
    (activity as MainActivity).zaPredaj(MainActivity.anketa.naziv,a)
}
    fun onError()
    {

    }

}

