// SeasonAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iecagto.R
import com.example.iecagto.datas.Season

class SeasonAdapter(private val seasons: MutableList<Season>) :
    RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

    // Interface to handle item clicks
    interface OnItemClickListener {
        fun onItemClick(season: Season)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.season_item, parent, false)
        return SeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasons[position]
        holder.textName.text = season.name
        holder.textDescription.text = season.description

        // Set click listener for the item
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(season)
        }
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    fun updateData(newSeasons: List<Season>) {
        seasons.clear()
        seasons.addAll(newSeasons)
        notifyDataSetChanged()
    }
}
