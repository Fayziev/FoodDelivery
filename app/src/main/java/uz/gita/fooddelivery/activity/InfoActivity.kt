package uz.gita.fooddelivery.activity

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.databinding.ActivityInfoBinding
import uz.gita.fooddelivery.presentation.viewmodel.InfoViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.InfoViewModelImpl

@AndroidEntryPoint
class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private var state = true
    private val viewModel: InfoViewModel by viewModels<InfoViewModelImpl>()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getSerializableExtra("data") as FoodData
        Glide.with(this).load(data.imageURL).into(binding.imageView)
        binding.name.text = data.name
        binding.cost.text = "${data.cost} so'm"
        binding.textViewCounter.text = "${data.count}x"
        binding.backBtn.setOnClickListener {
            finish()
        }
        state = data.isSelected
        if (data.isSelected) {
            binding.favouriteLayout.setBackgroundResource(R.drawable.background_favourite)
            binding.favouriteIcon.setImageResource(R.drawable.heartwhite)
            binding.favouriteText.setTextColor(parseColor("#FFFFFF"))
        } else {
            binding.favouriteLayout.setBackgroundResource(android.R.color.transparent)
            binding.favouriteIcon.setImageResource(R.drawable.heart)
            binding.favouriteText.setTextColor(parseColor("#FF000000"))
        }

        binding.favouriteLayout.setOnClickListener {
            state = !state
            if (state) {
                binding.favouriteLayout.setBackgroundResource(R.drawable.background_favourite)
                binding.favouriteIcon.setImageResource(R.drawable.heartwhite)
                binding.favouriteText.setTextColor(parseColor("#FFFFFF"))
                viewModel.addFavourite(data.id)
            } else {
                binding.favouriteLayout.setBackgroundResource(android.R.color.transparent)
                binding.favouriteIcon.setImageResource(R.drawable.heart)
                binding.favouriteText.setTextColor(parseColor("#FF000000"))
                viewModel.deleteFromFavourite(data.id)
            }
        }
        binding.imagePlus.setOnClickListener {
            viewModel.increaseCount(data.id)
            binding.textViewCounter.text = "${++data.count}x"
        }
        binding.imageMinus.setOnClickListener {
            if (data.count == 0L) return@setOnClickListener
            viewModel.decreaseCount(data.id)
            binding.textViewCounter.text = "${--data.count}x"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}