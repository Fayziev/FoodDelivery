package uz.gita.fooddelivery.presentation.ui.page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import uz.gita.fooddelivery.activity.InfoActivity
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.databinding.PageMainBinding
import uz.gita.fooddelivery.presentation.ui.adapter.AdsAdapter
import uz.gita.fooddelivery.presentation.ui.adapter.PopularAdapter
import uz.gita.fooddelivery.presentation.viewmodel.MainViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class HomePage : Fragment(R.layout.page_main) {
    private val binding by viewBinding(PageMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private var _adPage: AdPage? = null
    private val adPage get() = _adPage!!
    private val list = ArrayList<FoodData>()
    private val adapterPop = PopularAdapter(list)
    private val adapter by lazy {
        AdsAdapter(
            childFragmentManager,
            lifecycle,
            adPage,
            4
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerViewPopular.adapter = adapterPop
        binding.recyclerViewPopular.layoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        viewModel.getPopular()
        viewModel.adsData()
        adapterPop.setIncreaseItemClickListener {
            viewModel.increaseCount(it)
        }
        adapterPop.setDecreaseItemClickListener {
            viewModel.decreaseCount(it)
        }
        adapterPop.setListener {
            val intent = Intent(requireContext(), InfoActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
        viewModel.adsData.observe(viewLifecycleOwner, {
            val imageList = ArrayList<CarouselItem>()
            for (i in 0 until 4) {
                imageList.add(CarouselItem(it[i].imageURL))
            }
            _adPage = AdPage(imageList)
            binding.pager.adapter = adapter
        })
        viewModel.popularListLiveData.observe(viewLifecycleOwner, {
            Log.d("TTT", "onViewCreated: $it")
            list.clear()
            list.addAll(it)
            adapterPop.notifyDataSetChanged()
        })
    }
}