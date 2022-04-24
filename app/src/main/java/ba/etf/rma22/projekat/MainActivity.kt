package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.data.models.Korisnik
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.view.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){
    companion object{
        var korisnik : Korisnik = Korisnik(mutableListOf(), mutableListOf(), mutableListOf(),0)
        var stringGru : String = ""
        var stringIstra : String = ""
    }
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_acc)

        val fragments = mutableListOf(
            FragmentAnketa(),
            FragmentIstrazivanje()
        )

        viewPager = findViewById(R.id.pager)
        viewPager.offscreenPageLimit = 2
        viewPagerAdapter= ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter



        var doppelgangerPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position==0){
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje())
                    }, 500)
                }
            }
        }
        viewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)
    }
    fun refreshSecondFragmentText() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(1, FragmentPoruka( Grupa(stringGru, stringIstra)))
            }, 500)
        }

    }





