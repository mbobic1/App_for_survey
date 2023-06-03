package ba.etf.rma22.projekat


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AccountViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity(){
    companion object{
        var prog : Int = 0;
        var uzmiPitanjaBroj : Int =0;
        var brojiOdg : Int = 0
        var novaAnketa : Anketa = Anketa(0, "", "",Date(0 ,0,0), Date(0 ,0,0), Date(0 ,0,0), 0,"", 0, "")
        var anketa : Anketa = Anketa(0, "", "",Date(0 ,0,0), Date(0 ,0,0), Date(0 ,0,0), 0,"", 0, "")
        var sacuvaj: Sacuvaj = Sacuvaj(anketa, mutableListOf(), 0, false)
        var sacuvajLista : MutableList<Sacuvaj> = mutableListOf()
        var korisnik : Korisnik = Korisnik(mutableListOf(), mutableListOf(), mutableListOf(),0)
        var stringGru : String = ""
        var stringIstra : String = ""
        var godina : Int = 0
        var predaj : Int = 0
        var internet = false
     }
    var scope = CoroutineScope(Job() + Dispatchers.IO)
    private lateinit var viewPager : ViewPager2
    var accViewModel : AccountViewModel = AccountViewModel()

    var  viewPagerAdapter= ViewPagerAdapter( this)

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
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
        //isOnline(this)
        AccountRepository.context=this;
        IstrazivanjeIGrupaRepository.context=this
        AnketaRepository.context=this
        internet=isOnline(this)
        val data : Uri? = intent?.data
        if(data!=null){
            val strExtra = intent.getStringExtra("payload")
            if(strExtra!=null){
                accViewModel.postaviHash(strExtra)
                accViewModel.upisiUBazuPodataka(onSuccess = ::onSuccess, onError = ::onError )
            }
            }else{
                accViewModel.upisiUBazuPodataka(onSuccess = ::onSuccess, onError = ::onError)

            }
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)
    }

    fun onSuccess(jel : Boolean){
        scope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Upisan u bazu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onError(){
        println("Ne radi kako treba")
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
        viewPager.adapter=viewPagerAdapter
        viewPager.currentItem=0

    }

}






