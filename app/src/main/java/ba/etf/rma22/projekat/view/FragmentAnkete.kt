package ba.etf.rma22.projekat.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.UpisIstrazivanje
import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeViewModel

private const val NUM_PAGES=2

class FragmentAnketa : Fragment() {
    private lateinit var viewPager : ViewPager2
    private lateinit var listaAnketa :  RecyclerView
    private lateinit var anketaAdapter: AnketaListAdapter
    private var pitanjeViewModel : PitanjeViewModel = PitanjeViewModel()
    private var anketaViewModel : AnketaViewModel= AnketaViewModel()
    val podatci:Array<String> = arrayOf(
        "Sve moje ankete",
        "Sve ankete",
        "Urađene ankete",
        "Buduće ankete",
        "Prošle ankete")

    override fun onResume() {
        super.onResume()
                 anketaViewModel.getMyAnkete(onSuccess = ::update, onError = ::onError)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.activity_main, container, false)
        listaAnketa = view.findViewById(R.id.listaAnketa)
        listaAnketa.layoutManager = GridLayoutManager(activity, 2)
        anketaAdapter = AnketaListAdapter(arrayListOf(), activity as MainActivity)
        listaAnketa.adapter=anketaAdapter
        anketaViewModel.getMyAnkete(onSuccess = ::update, onError = ::onError)

        var spinner: Spinner;
        var adapter= ArrayAdapter(view.context, android.R.layout.simple_spinner_item,podatci)
        spinner = view.findViewById(R.id.filterAnketa);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter);
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent!=null){
                    if(position==0){
                        anketaViewModel.getMyAnkete(onSuccess = ::update, onError = ::onError)
                    }
                    else if(position==1){
                        anketaViewModel.getAll(onSuccess = ::update, onError = ::onError)
                    }
                    else if(position==2){
                        anketaViewModel.getDone(onSuccess = ::update, onError = ::onError)
                    }
                    else if(position==3){
                        anketaViewModel.getFuture(onSuccess = ::update, onError = ::onError)
                    }
                    else if(position==4){
                        anketaViewModel.getProsle(onSuccess = ::update, onError = ::onError)
                    }
                }
            }

            override fun onNothingSelected(parent : AdapterView<*>?) {

            }
        }

        return view;
    }
    companion object {
        fun newInstance(): FragmentAnketa = FragmentAnketa()
    }

    fun update(ankete1 : List<Anketa>){
        anketaAdapter.updateAnketa(ankete1)
    }
    fun onError(){
        println("Nije uspjelo")
    }
}



