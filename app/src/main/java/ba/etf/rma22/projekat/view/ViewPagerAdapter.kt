package ba.etf.rma22.projekat.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeRepository
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel

class ViewPagerAdapter(
     activity: AppCompatActivity
) : FragmentStateAdapter(activity) {
    var activity=activity
    private lateinit var pitanjeRepository: PitanjeRepository
    var fragments : MutableList<Fragment> = mutableListOf(FragmentAnketa.newInstance(),FragmentIstrazivanje.newInstance())
    override fun getItemCount(): Int {
        return fragments.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
    fun refreshFragment(index: Int, fragment: Fragment) {
        fragments[index] = fragment
        notifyItemChanged(index)
    }
    fun vratiIstrazivanje(){

        if(fragments.size>1 && fragments[1] is FragmentPoruka)
        {

            fragments[1]=FragmentIstrazivanje.newInstance()
            notifyItemChanged(1)

        }
    }
    fun dugmePredaj(t1 :String, t2:String){
        fragments.clear()
        fragments.add(FragmentAnketa())
        fragments.add(FragmentPoruka.newInstance(t1,t2, 1))
        notifyItemChanged(1)
       // println("snkrs34343434343434=${MainActivity.sacuvaj.anketa.naziv}   =${MainActivity.sacuvajLista.size} =${MainActivity.sacuvajLista[0].anketa.naziv}")

    }
    fun dugmeZaustavi(){
        fragments.clear()
        fragments.add(FragmentAnketa())
        fragments.add(FragmentIstrazivanje())
        notifyDataSetChanged()
    }
    fun dajPitanja(){
        PitanjeAnketaViewModel().getPitanjaV2(MainActivity.anketa.id, onSucces = ::oe, onError = ::onError)
       }
    fun oe(a:List<Pitanje>, odg : List<Odgovor>, anketaTaken: AnketaTaken)
    {
        fragments.clear()
        for( i in a){
            if(odg.map{it->it.PitanjeId}.contains(i.id)) {
                fragments.add(
                    FragmentPitanje(
                        i,
                        odg.last { it -> it.PitanjeId == i.id }.odgovoreno,
                        anketaTaken
                    )
                )
                MainActivity.uzmiPitanjaBroj++;
            }else {
                fragments.add(FragmentPitanje(i, -1, anketaTaken))
                MainActivity.uzmiPitanjaBroj++;
            }
        }
    fragments.add(FragmentPredaj())
    notifyDataSetChanged()
    (activity as MainActivity).evo()

    }
fun onError()
{

}



}