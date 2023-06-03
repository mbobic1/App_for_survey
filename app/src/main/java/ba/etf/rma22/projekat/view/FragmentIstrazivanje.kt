package ba.etf.rma22.projekat.view

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeIGrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import ba.etf.rma22.projekat.viewmodel.KorisnikViewModel

class FragmentIstrazivanje : Fragment() {
    private var odabir: Boolean = false;
    var fsdjal= mutableListOf<Grupa>()
    var uzmiIstr1 = mutableListOf<Istrazivanje>()
    private var istrazivanjeViewModel: IstrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel: GrupaViewModel = GrupaViewModel()
    private var korisnikViewModel: KorisnikViewModel = KorisnikViewModel()
    private var istrazivanjeIGrupa : IstrazivanjeIGrupaViewModel = IstrazivanjeIGrupaViewModel()
    private lateinit var spinnerGod : Spinner;
    private lateinit var spinnerG : Spinner;
    private lateinit var spinnerI : Spinner;
    private lateinit var button : Button;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        var view= inflater.inflate(R.layout.istrazivanje, container, false)
        var tacno : Boolean = false
        var tacno1 : Boolean = false
        var tacno2 : Boolean = false

        spinnerGod=view.findViewById(R.id.odabirGodina)
        spinnerG=view.findViewById(R.id.odabirGrupa)
        spinnerI=view.findViewById(R.id.odabirIstrazivanja)
        button=view.findViewById(R.id.dodajIstrazivanjeDugme)
        val god:Array<String> = arrayOf(
            " ",
            "1",
            "2",
            "3",
            "4",
            "5")
        val adapter= ArrayAdapter(view.context, android.R.layout.simple_spinner_item, god);
        button.isEnabled = false
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGod.setAdapter(adapter);
        spinnerGod.setSelection(MainActivity.korisnik.godinaStudiranja)
        spinnerGod.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tacno = true

                if (parent != null) {
                    if (position == 0) {
                        var arr1 = ArrayAdapter(
                            parent.context, android.R.layout.simple_spinner_item,
                            mutableListOf<String>()
                        )
                        arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerI.adapter = arr1
                        var arr2 = ArrayAdapter(
                            parent.context,
                            android.R.layout.simple_spinner_item,
                            mutableListOf<String>()
                        )
                        arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerG.adapter = arr2
                        button.isEnabled = false

                    } else {

                        istrazivanjeIGrupa.getIstrazivanjeByGodina(
                            position,
                            onSucces = ::istrazivanjePoGodini,
                            onError = ::onError,
                            parent.context
                        )
                        spinnerI.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    istrazivanjeIGrupa.getGrupeZaIstrazivanjeV2(
                                        position,
                                        onSucces = ::grupeOdIstrazivanja,
                                        onError = ::onError,
                                        parent.context
                                    )

                                }


                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }
                            }
                    }

                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        button.setOnClickListener {
            istrazivanjeIGrupa.upisiUGrupu(fsdjal[spinnerG.selectedItemPosition].id, onSuccess = ::upisiGrupu1,onError =::onError)
            MainActivity.stringGru=fsdjal[spinnerG.selectedItemPosition].naziv
            MainActivity.stringIstra=uzmiIstr1[spinnerI.selectedItemPosition].naziv

            (activity as MainActivity).refreshSecondFragmentText()
        }
        return view;
}
    companion object{
        fun newInstance() : FragmentIstrazivanje{
            var novi = FragmentIstrazivanje()
            return novi
        }
    }
    fun vratiGrupu(): String {
        return spinnerG.selectedItem.toString()
    }

    fun vratiIstrazivanje(): String {
        return spinnerI.selectedItem.toString()
    }

    fun upisiGrupu1(){

    }

    fun istrazivanjePoGodini(istraz : List<Istrazivanje>, context : Context){
        val uzmi = istraz
        uzmiIstr1 = istraz.toMutableList()
        if (uzmi.size == 0){
            var arr1 = ArrayAdapter(context, android.R.layout.simple_spinner_item,
                mutableListOf<String>())
            arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerI.adapter=arr1
            var  arr2= ArrayAdapter(context, android.R.layout.simple_spinner_item,mutableListOf<String>())
            arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerG.adapter=arr2
            button.isEnabled=false
        }
        else {
            var niz = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                uzmi.map { istar -> istar.naziv }.toMutableList()
            )
            niz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerI.setAdapter(niz)
        }
    }
    fun grupeOdIstrazivanja(grupe1 : List<Grupa>, context: Context){
        var uzmi = grupe1
        fsdjal=grupe1.toMutableList()
        var niz1 = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item, uzmi.map { grupe12 -> grupe12.naziv }.toMutableList())
        niz1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerG.setAdapter(niz1)
        button.isEnabled=true

    }

    fun onError(){
        println("Nije uspjelo")
    }

}