package ba.etf.rma22.projekat.view

import android.content.Intent
import android.os.Bundle
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
        anketaAdapter.updateAnketa(anketaViewModel.getMyAnkete())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.activity_main, container, false)
        listaAnketa = view.findViewById(R.id.listaAnketa)
        listaAnketa.layoutManager = GridLayoutManager(activity, 2)
        anketaAdapter = AnketaListAdapter(arrayListOf())
        listaAnketa.adapter=anketaAdapter
        anketaAdapter.updateAnketa(ankete())

        var spinner: Spinner;
        var adapter= ArrayAdapter(view.context, android.R.layout.simple_spinner_item,podatci)
        spinner = view.findViewById(R.id.filterAnketa);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter);
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent!=null){
                    if(position==0){
                        anketaAdapter.updateAnketa(anketaViewModel.getMyAnkete())
                    }
                    else if(position==1){
                        anketaAdapter.updateAnketa(anketaViewModel.getAll())
                    }
                    else if(position==2){
                        anketaAdapter.updateAnketa(anketaViewModel.getDone())
                    }
                    else if(position==3){
                        anketaAdapter.updateAnketa(anketaViewModel.getFuture())
                    }
                    else if(position==4){
                        anketaAdapter.updateAnketa(anketaViewModel.getNotTaken())
                    }
                }
            }

            override fun onNothingSelected(parent : AdapterView<*>?) {

            }
        }


        var duzina = pitanjeViewModel.getVelicinu()
        val fragments = mutableListOf<Fragment>()



        return view;
    }
    companion object {
        fun newInstance(): FragmentAnketa = FragmentAnketa()
    }

}



