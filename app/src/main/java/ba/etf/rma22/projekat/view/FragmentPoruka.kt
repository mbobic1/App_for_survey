package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

class FragmentPoruka : Fragment() {
    var grupa1 : String = ""
    var istrazivanje1 : String = ""
    var predaj : Int = 0
    private lateinit var poruka1 : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val bundle : Bundle = requireArguments()
        val view=inflater.inflate(R.layout.poruka, container,false)
        poruka1 = view.findViewById(R.id.tvPoruka)
        if(predaj==0) {
            poruka1.setText("Uspješno ste upisani u grupu ${grupa1} istraživanja ${istrazivanje1}!")
        }
        else{
            poruka1.setText("Završili ste anketu ${grupa1} u okviru istraživanja ${istrazivanje1}")
        }
        return view
    }
    companion object{
        fun newInstance( grupa : String, istrazivanje: String , predaj : Int) : FragmentPoruka{
            val novi = FragmentPoruka()
            novi.grupa1=grupa
            novi.istrazivanje1=istrazivanje
            novi.predaj=predaj
            return novi
        }

    }


}