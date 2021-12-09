package uz.gita.fooddelivery.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.app.App
import uz.gita.fooddelivery.data.FoodData

class PopularAdapter(private val list: List<FoodData>) : RecyclerView.Adapter<PopularAdapter.VH>() {
    private var listener: ((FoodData) -> Unit)? = null
    private var increaseListener: ((Long) -> Unit)? = null
    private var decreaseListener: ((Long) -> Unit)? = null


    @SuppressLint("SetTextI18n")
    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: RoundedImageView = view.findViewById(R.id.roundedImageViewFood)
        private val textViewName: TextView = view.findViewById(R.id.textViewName)
        private val textViewCost: TextView = view.findViewById(R.id.textViewCost)
        private val textViewCounter: TextView = view.findViewById(R.id.textViewCounter)
        private val buttonAdd: AppCompatButton = view.findViewById(R.id.buttonAdd)
        private val imageMinus: AppCompatButton = view.findViewById(R.id.imageMinus)
        private val imagePlus: AppCompatButton = view.findViewById(R.id.imagePlus)

        init {
            itemView.setOnClickListener {
                listener?.invoke(list[absoluteAdapterPosition])
            }

            buttonAdd.setOnClickListener {
                buttonAdd.visibility = View.GONE
                imageMinus.visibility = View.VISIBLE
                imagePlus.visibility = View.VISIBLE
                textViewCounter.visibility=View.VISIBLE
                increaseListener?.invoke(list[absoluteAdapterPosition].id)
            }
            imageMinus.setOnClickListener {
                decreaseListener?.invoke(list[absoluteAdapterPosition].id)
            }
            imagePlus.setOnClickListener {
                increaseListener?.invoke(list[absoluteAdapterPosition].id)
            }
        }

        fun bind() {
            list[absoluteAdapterPosition].apply {
                Glide.with(App.instance).load(imageURL).into(imageView)
                textViewName.text = name
                textViewCost.text = "$cost so'm"
                if (count == 0L) {
                    buttonAdd.visibility = View.VISIBLE
                    imageMinus.visibility = View.GONE
                    imagePlus.visibility = View.GONE
                    textViewCounter.visibility = View.GONE
                } else {
                    buttonAdd.visibility = View.GONE
                    imageMinus.visibility = View.VISIBLE
                    imagePlus.visibility = View.VISIBLE
                    textViewCounter.visibility = View.VISIBLE
                }
                textViewCounter.text = "${count}x"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()
    override fun getItemCount(): Int = list.size

    fun setListener(f: (FoodData) -> Unit) {
        listener = f
    }

    fun setDecreaseItemClickListener(f: (Long) -> Unit) {
        decreaseListener = f
    }

    fun setIncreaseItemClickListener(f: (Long) -> Unit) {
        increaseListener = f
    }
}