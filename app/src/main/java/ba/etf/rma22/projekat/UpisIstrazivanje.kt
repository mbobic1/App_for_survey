package ba.etf.rma22.projekat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma22.projekat.MainActivity.Companion.korisnik
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository.Companion.getIstrazivanjeByGodina
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import ba.etf.rma22.projekat.viewmodel.KorisnikViewModel

class UpisIstrazivanje : AppCompatActivity() {
    private var odabir: Boolean = false;
    private var istrazivanjeViewModel: IstrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel: GrupaViewModel = GrupaViewModel()
    private var korisnikViewModel: KorisnikViewModel = KorisnikViewModel()
    private lateinit var spinnerGod : Spinner;
    private lateinit var spinnerG : Spinner;
    private lateinit var spinnerI : Spinner;
    private lateinit var button : Button;

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.istrazivanje)

            spinnerGod=findViewById(R.id.odabirGodina)
            spinnerG=findViewById(R.id.odabirGrupa)
            spinnerI=findViewById(R.id.odabirIstrazivanja)
            button=findViewById(R.id.dodajIstrazivanjeDugme)
            val god:Array<String> = arrayOf(
                " ",
                "1",
                "2",
                "3",
                "4",
                "5")
            val adapter=ArrayAdapter(this, android.R.layout.simple_spinner_item, god);
           button.isEnabled = false
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGod.setAdapter(adapter);
            spinnerGod.setSelection(korisnik.godinaStudiranja)
            spinnerGod.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                      if(parent!=null){
                         if(position==0){
                             var arr1 = ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,
                                 mutableListOf<String>())
                             arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                             spinnerI.adapter=arr1
                             var  arr2= ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,mutableListOf<String>())
                             arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                             spinnerG.adapter=arr2
                             button.isEnabled=false

                         }
                         else if(position==1) {
                            var uzmi =
                                istrazivanjeViewModel.getIstrazivanjeByGodina(1).toMutableList()
                            uzmi.removeAll(istrazivanjeViewModel.getUpisani())

                            if (uzmi.size == 0){
                                var arr1 = ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,
                                    mutableListOf<String>())
                                arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.adapter=arr1
                                var  arr2= ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,mutableListOf<String>())
                                arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerG.adapter=arr2
                                button.isEnabled=false
                            }
                            else{
                            var niz = ArrayAdapter(
                                parent.context,
                                android.R.layout.simple_spinner_item,
                                uzmi.map { istar -> istar.naziv }.toMutableList()
                            )
                            niz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinnerI.setAdapter(niz)

                            spinnerI.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        p0: AdapterView<*>?,
                                        p1: View?,
                                        p2: Int,
                                        p3: Long
                                    ) {
                                       /* if (p0 != null) {
                                            var niz1 = ArrayAdapter(
                                                parent.context,
                                                android.R.layout.simple_spinner_item,
                                                grupaViewModel.getGroupsByIstrazivanje(
                                                    p0.getItemAtPosition(p2) as String
                                                ).map { istar -> istar.naziv })
                                            niz1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                            spinnerG.setAdapter(niz1)
                                            button.isEnabled=true

                                        }*/
                                    }

                                    override fun onNothingSelected(p0: AdapterView<*>?) {
                                        TODO("Not yet implemented")
                                    }
                                }
                        }


                        }
                        else if(position==2){
                            var uzmi =
                                istrazivanjeViewModel.getIstrazivanjeByGodina(2).toMutableList()
                            uzmi.removeAll(istrazivanjeViewModel.getUpisani())

                            if (uzmi.size == 0){
                                var arr1 = ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,
                                    mutableListOf<String>())
                                arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.adapter=arr1
                                var  arr2= ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,mutableListOf<String>())
                                arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerG.adapter=arr2
                                button.isEnabled=false
                            }
                            else{
                                var niz = ArrayAdapter(
                                    parent.context,
                                    android.R.layout.simple_spinner_item,
                                    uzmi.map { istar -> istar.naziv }.toMutableList()
                                )
                                niz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.setAdapter(niz)

                                spinnerI.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                            /*if (p0 != null) {
                                                var niz1 = ArrayAdapter(
                                                    parent.context,
                                                    android.R.layout.simple_spinner_item,
                                                    grupaViewModel.getGroupsByIstrazivanje(
                                                        p0.getItemAtPosition(p2) as String
                                                    ).map { istar -> istar.naziv })
                                                niz1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                                spinnerG.setAdapter(niz1)
                                                button.isEnabled=true

                                            }*/
                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                            TODO("Not yet implemented")
                                        }
                                    }
                            }

                        }
                        else if(position==3){
                            var uzmi =
                                istrazivanjeViewModel.getIstrazivanjeByGodina(3).toMutableList()
                            uzmi.removeAll(istrazivanjeViewModel.getUpisani())

                            if (uzmi.size == 0){
                                var arr1 = ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,
                                    mutableListOf<String>())
                                arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.adapter=arr1
                                var  arr2= ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,mutableListOf<String>())
                                arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerG.adapter=arr2
                                button.isEnabled=false
                            }
                            else{
                                var niz = ArrayAdapter(
                                    parent.context,
                                    android.R.layout.simple_spinner_item,
                                    uzmi.map { istar -> istar.naziv }.toMutableList()
                                )
                                niz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.setAdapter(niz)

                                spinnerI.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                            if (p0 != null) {
                                                var niz1 = grupaViewModel.getGroupsByIstrazivanje(
                                                    p0.getItemAtPosition(p2) as String
                                                )?.let {
                                                    ArrayAdapter(
                                                        parent.context,
                                                        android.R.layout.simple_spinner_item,
                                                        it.map { istar -> istar.naziv })
                                                }
                                                with(niz1) {
                                                    //setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                                }
                                                spinnerG.setAdapter(niz1)
                                                button.isEnabled=true

                                            }
                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                            TODO("Not yet implemented")
                                        }
                                    }
                            }

                        }
                        else if(position==4){
                            var uzmi =
                                istrazivanjeViewModel.getIstrazivanjeByGodina(4).toMutableList()
                            uzmi.removeAll(istrazivanjeViewModel.getUpisani())

                            if (uzmi.size == 0){
                                var arr1 = ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,
                                    mutableListOf<String>())
                                arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.adapter=arr1
                                var  arr2= ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,mutableListOf<String>())
                                arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerG.adapter=arr2
                                button.isEnabled=false
                            }
                            else{
                                var niz = ArrayAdapter(
                                    parent.context,
                                    android.R.layout.simple_spinner_item,
                                    uzmi.map { istar -> istar.naziv }.toMutableList()
                                )
                                niz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.setAdapter(niz)

                                spinnerI.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                           /* if (p0 != null) {
                                                var niz1 = ArrayAdapter(
                                                    parent.context,
                                                    android.R.layout.simple_spinner_item,
                                                    grupaViewModel.getGroupsByIstrazivanje(
                                                        p0.getItemAtPosition(p2) as String
                                                    ).map { istar -> istar.naziv })
                                                niz1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                                spinnerG.setAdapter(niz1)
                                                button.isEnabled=true

                                            }*/
                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                            TODO("Not yet implemented")
                                        }
                                    }
                            }

                        }
                        else if(position==5){
                            var uzmi =
                                istrazivanjeViewModel.getIstrazivanjeByGodina(5).toMutableList()
                            uzmi.removeAll(istrazivanjeViewModel.getUpisani())

                            if (uzmi.size == 0){
                                var arr1 = ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,
                                    mutableListOf<String>())
                                arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.adapter=arr1
                                var  arr2= ArrayAdapter(parent.context, android.R.layout.simple_spinner_item,mutableListOf<String>())
                                arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerG.adapter=arr2
                                button.isEnabled=false
                            }
                            else{
                                var niz = ArrayAdapter(
                                    parent.context,
                                    android.R.layout.simple_spinner_item,
                                    uzmi.map { istar -> istar.naziv }.toMutableList()
                                )
                                niz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                spinnerI.setAdapter(niz)

                                spinnerI.onItemSelectedListener =
                                    object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(
                                            p0: AdapterView<*>?,
                                            p1: View?,
                                            p2: Int,
                                            p3: Long
                                        ) {
                                           /* if (p0 != null) {
                                                var niz1 = ArrayAdapter(
                                                    parent.context,
                                                    android.R.layout.simple_spinner_item,
                                                    grupaViewModel.getGroupsByIstrazivanje(
                                                        p0.getItemAtPosition(p2) as String
                                                    ).map { istar -> istar.naziv })
                                                niz1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                                spinnerG.setAdapter(niz1)
                                                button.isEnabled=true

                                            }*/
                                        }

                                        override fun onNothingSelected(p0: AdapterView<*>?) {
                                            TODO("Not yet implemented")
                                        }
                                    }
                            }

                        }

                    }
                }

                override fun onNothingSelected(parent : AdapterView<*>?) {
                }
            }

                button.setOnClickListener {
                    korisnik.godinaStudiranja= spinnerGod.selectedItem.toString().toInt()

                    korisnikViewModel.upisiKorisnika(
                        spinnerG.selectedItem.toString(),
                        spinnerI.selectedItem.toString(),
                        spinnerGod.selectedItem.toString().toInt()
                    )
                    intent.putExtra("returnDefault", odabir)
                    setResult(RESULT_OK, intent)
                    finish()
                }


        }
}