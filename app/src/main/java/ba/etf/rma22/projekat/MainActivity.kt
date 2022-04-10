package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.data.models.Korisnik
import ba.etf.rma22.projekat.view.AnketaListAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){
    companion object{
        var korisnik : Korisnik = Korisnik(mutableListOf(), mutableListOf(), mutableListOf(),0)
    }
    private var anketaViewModel: AnketaViewModel= AnketaViewModel()
    private lateinit var listaAnketa: RecyclerView
    private lateinit var anketaAdapter: AnketaListAdapter

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner: Spinner;
        var adapter= ArrayAdapter(this, android.R.layout.simple_spinner_item,podatci)
        spinner = findViewById(R.id.filterAnketa);
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



         listaAnketa = findViewById(R.id.listaAnketa)
         listaAnketa.layoutManager= GridLayoutManager(
            this,
            2
        )
        anketaAdapter = AnketaListAdapter(arrayListOf())
        listaAnketa.adapter = anketaAdapter
        anketaAdapter.updateAnketa(ankete())



        val dugme: FloatingActionButton;
        dugme = findViewById(R.id.upisDugme)
        dugme.setOnClickListener{
            val intent = Intent(this, UpisIstrazivanje::class.java)
            startActivity(intent)
        }
    }


}




