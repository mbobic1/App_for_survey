package ba.etf.rma22.projekat.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.repositories.PitanjeRepository
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class ViewPagerAdapter(
     activity: AppCompatActivity
) : FragmentStateAdapter(activity) {
    private lateinit var pitanjeRepository: PitanjeRepository
    var fragments : MutableList<Fragment> = mutableListOf(FragmentAnketa.newInstance(),FragmentIstrazivanje.newInstance())
    override fun getItemCount(): Int {
        return fragments.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
    fun refreshFragment(index: Int, fragment: Fragment) {
        println("Usla u adaoter")
        fragments[index] = fragment
        notifyItemChanged(index)
    }
    fun vratiIstrazivanje(){
        if(fragments[1] is FragmentPoruka)
        {
            fragments[1]=FragmentIstrazivanje.newInstance()
            notifyItemChanged(1)

        }
    }
    fun dugmePredaj(){
        fragments.clear()
        fragments.add(FragmentAnketa())
        fragments.add(FragmentPoruka.newInstance(MainActivity.anketa.naziv, MainActivity.anketa.nazivIstrazivanja, MainActivity.predaj))
        notifyItemChanged(1)
    }
    fun dugmeZaustavi(){
        fragments.clear()
        fragments.add(FragmentAnketa())
        fragments.add(FragmentIstrazivanje())
        notifyDataSetChanged()
    }
    fun dajPitanja(){
        fragments.clear()
        var pitanja = PitanjeAnketaViewModel().getPitanja(MainActivity.anketa.naziv, MainActivity.anketa.nazivIstrazivanja)
        for( i in pitanja){
            fragments.add(FragmentPitanje(i))
        }
        fragments.add(FragmentPredaj())
        notifyDataSetChanged()
    }





}