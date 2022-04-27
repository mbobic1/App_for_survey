package ba.etf.rma22.projekat


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import ba.etf.rma22.projekat.data.models.Korisnik
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class MainActivity : AppCompatActivity(){
    companion object{
        var korisnik : Korisnik = Korisnik(mutableListOf(), mutableListOf(), mutableListOf(),0)
        var stringGru : String = ""
        var stringIstra : String = ""
        var godina : Int = 0
        var predaj : Int = 0
        var anketa : Anketa = Anketa("", "", Date(0 ,0,0), Date(0 ,0,0), Date(0 ,0,0), 0,"", 0f)
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

        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=1

    }

    fun zaPredaj(){
        viewPagerAdapter.dugmePredaj()
        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=1
    }
    fun zaZaustavi(){
        viewPagerAdapter.dugmeZaustavi()
        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=0
    }
    fun refreshAnketaFragmentPitanje() {
        //println("Tadi li ")
         viewPagerAdapter.dajPitanja()
         viewPager.adapter=viewPagerAdapter
    }

}






