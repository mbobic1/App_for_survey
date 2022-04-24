package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

class FragmentPoruka (var grupa : Grupa ) : Fragment() {
    private lateinit var poruka1 : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val bundle : Bundle = requireArguments()
        val view=inflater.inflate(R.layout.poruka, container,false)
        poruka1 = view.findViewById(R.id.tvPoruka)
        poruka1.setText("Uspješno ste upisani u grupu ${grupa.naziv} istraživanja ${grupa.nazivIstrazivanja}!")
        return view
    }
    companion object{
        fun newInstance(grupa: Grupa) : FragmentPoruka = FragmentPoruka(grupa)
    }


}