package uz.gita.fooddelivery.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.app.App
import uz.gita.fooddelivery.data.FoodData

class FavouriteAdapter(private val list: List<FoodData>) : RecyclerView.Adapter<FavouriteAdapter.VH>() {
    private var listener: ((FoodData) -> Unit)? = null
    private var increaseListener: ((Long) -> Unit)? = null
    private var decreaseListener: ((Long) -> Unit)? = null


    @SuppressLint("SetTextI18n")
    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: RoundedImageView = view.findViewById(R.id.imageViewFavorite)
        private val textViewName: TextView = view.findViewById(R.id.textViewName)
        private val textViewCost: TextView = view.findViewById(R.id.textViewCost)
        private val textViewCounter: TextView = view.findViewById(R.id.textViewCounter)
        private val buttonAdd: TextView = view.findViewById(R.id.addBtn)
        private val imageMinus: ImageView = view.findViewById(R.id.imageMinus)
        private val imagePlus: ImageView = view.findViewById(R.id.imagePlus)
        private val back: ImageView = view.findViewById(R.id.back)

        init {
            itemView.setOnClickListener {
                listener?.invoke(list[absoluteAdapterPosition])
            }

            buttonAdd.setOnClickListener {
                buttonAdd.visibility = GONE
                back.visibility = VISIBLE
                imageMinus.visibility = VISIBLE
                imagePlus.visibility = VISIBLE
                textViewCounter.visibility = VISIBLE
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
                    buttonAdd.visibility = VISIBLE
                    back.visibility = GONE
                    textViewCounter.visibility = GONE
                    imageMinus.visibility = GONE
                    imagePlus.visibility = GONE
                } else {
                    buttonAdd.visibility = GONE
                    back.visibility = VISIBLE
                    textViewCounter.visibility = VISIBLE
                    imageMinus.visibility = VISIBLE
                    imagePlus.visibility = VISIBLE
                }
                textViewCounter.text = "${count}x"
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false))

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