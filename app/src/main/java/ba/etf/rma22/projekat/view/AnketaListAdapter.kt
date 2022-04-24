package ba.etf.rma22.projekat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import java.text.SimpleDateFormat
import java.util.*

class AnketaListAdapter(
    var ankete: List<Anketa>
) : RecyclerView.Adapter<AnketaListAdapter.AnketaViewHolder>() {

    var ispisFormat =  SimpleDateFormat("dd.MM.yyyy.")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val itemHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.anketa, parent, false)
        return AnketaViewHolder(itemHolder);
    }

     fun prog( progres: Float): Int{
        val povratni : Int=(progres*100).toInt()
         for(i in 100 downTo 0 step 20){
             if(i-povratni<10) {
                 return i;
             }
             else if((i-povratni<10) && i<0){
                 return i*(-1);
             }
         }
         return 0;
     }

    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        val anketa: Anketa =ankete.get(position);
        val trenutniDatum : Date=Calendar.getInstance().run{ time };
        var id: Int=0;
        holder.itemView.setOnClickListener{

        }
        holder.anketaName.text=anketa.naziv;
        holder.anketaProgress.progress=prog(ankete[position].progres)
        holder.anketaIstrazivanje.text=ankete[position].nazivIstrazivanja;
        if(trenutniDatum<ankete[position].datumPocetak){
            holder.anketaDatum.text="Vrijeme aktiviranja: "+ ispisFormat.format(ankete[position].datumPocetak)
            holder.anketaSlika.setImageResource(R.drawable.zuta);
        }
        else if(ankete[position].progres.compareTo(1.0)==0){
            holder.anketaDatum.text="Anketa urađena: "+ ispisFormat.format(ankete[position].datumKraj)
            holder.anketaSlika.setImageResource(R.drawable.plava);
        }
        else if(trenutniDatum<ankete[position].datumKraj){
            holder.anketaDatum.text="Vrijeme zatvaranja: "+ ispisFormat.format(ankete[position].datumKraj)
            holder.anketaSlika.setImageResource(R.drawable.zelena);
        }
        else{
            holder.anketaDatum.text="Anketa zatvorena: "+ ispisFormat.format(ankete[position].datumKraj)
            holder.anketaSlika.setImageResource(R.drawable.crvena)
        }


    }
    fun updateAnketa(novaAnkete: List<Anketa>) {
        this.ankete = novaAnkete
        notifyDataSetChanged()
    }
    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val anketaName:TextView=itemView.findViewById(R.id.nazivAnketa)
        val anketaDatum:TextView=itemView.findViewById(R.id.datumAnekte)
        val anketaProgress: ProgressBar=itemView.findViewById(R.id.progresZavrsetka)
        val anketaSlika: ImageView=itemView.findViewById(R.id.stanjeAnkete);
        val anketaIstrazivanje: TextView=itemView.findViewById(R.id.nazivAnkete);


    }

    override fun getItemCount(): Int {
        return ankete.size
    }
}


