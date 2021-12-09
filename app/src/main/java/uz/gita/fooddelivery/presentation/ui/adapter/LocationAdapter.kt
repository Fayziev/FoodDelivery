package uz.gita.fooddelivery.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.data.LocationData

class LocationAdapter(private val list: List<LocationData>) : RecyclerView.Adapter<LocationAdapter.VH>() {
    private var listener: ((LocationData) -> Unit)? = null

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val locationName: TextView = view.findViewById(R.id.locationName)
        private val locationAddress: TextView = view.findViewById(R.id.locationAddress)
        private val locationTime: TextView = view.findViewById(R.id.timeText)

        init {
            itemView.setOnClickListener {
                listener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() {
            locationName.text = list[absoluteAdapterPosition].name
            locationAddress.text = list[absoluteAdapterPosition].description
            locationTime.text = list[absoluteAdapterPosition].time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()
    override fun getItemCount(): Int = list.size

    fun setListener(f: (LocationData) -> Unit) {
        listener = f
    }
}