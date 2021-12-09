package uz.gita.fooddelivery.presentation.ui.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.databinding.ItemAdBinding
import uz.gita.fooddelivery.tools.scope

class AdPage(private val imageList: List<CarouselItem>) : Fragment(R.layout.item_ad) {
    private val binding by viewBinding(ItemAdBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        imageViewAd.autoPlay=true
        imageViewAd.setData(imageList)
    }
}
