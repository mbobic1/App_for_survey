package ba.etf.rma22.projekat


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainActivity : AppCompatActivity(){
    companion object{
        var prog : Int = 0;
        var anketa : Anketa = Anketa(0, "", "",Date(0 ,0,0), Date(0 ,0,0), Date(0 ,0,0), 0,"", 0f, "")
        var sacuvaj: Sacuvaj = Sacuvaj(anketa, mutableListOf(), 0, false)
        var sacuvajLista : MutableList<Sacuvaj> = mutableListOf()
        var korisnik : Korisnik = Korisnik(mutableListOf(), mutableListOf(), mutableListOf(),0)
        var stringGru : String = ""
        var stringIstra : String = ""
        var godina : Int = 0
        var predaj : Int = 0

     }
    private lateinit var viewPager : ViewPager2


     var  viewPagerAdapter= ViewPagerAdapter( this)

    var doppelgangerPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if(position==0){
                Handler(Looper.getMainLooper()).postDelayed({
                    viewPagerAdapter.vratiIstrazivanje() }, 500)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_acc)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)
    }



    fun refreshSecondFragmentText() {
        viewPagerAdapter.refreshFragment(
                1,
            FragmentPoruka.newInstance(stringGru, stringIstra, predaj)
        )
        viewPagerAdapter.refreshFragment(
            0,
            FragmentAnketa.newInstance()
        )

        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=1

    }

    fun zaPredaj(t4:String,t2:String,){
        viewPagerAdapter.dugmePredaj(t4,t2)
        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=1
    }
    fun zaZaustavi(){
        viewPagerAdapter.dugmeZaustavi()
        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=0
    }
    fun refreshAnketaFragmentPitanje() {
         viewPagerAdapter.dajPitanja()
         viewPager.adapter=viewPagerAdapter
    }
    fun evo()
    {
        println("uoyf;tftyftfttttttttttttttttt")
        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=0

    }

}






