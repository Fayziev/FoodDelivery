package uz.gita.fooddelivery.presentation.ui.page

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.activity.InfoActivity
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.databinding.PageFavoriteBinding
import uz.gita.fooddelivery.presentation.ui.adapter.FavouriteAdapter
import uz.gita.fooddelivery.presentation.viewmodel.FavouriteViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.FavouriteViewModelImpl
import uz.gita.fooddelivery.tools.scope

@AndroidEntryPoint
class FavoritePage : Fragment(R.layout.page_favorite) {
    private val binding by viewBinding(PageFavoriteBinding::bind)
    private val list = ArrayList<FoodData>()
    private val adapter = FavouriteAdapter(list)
    private val viewModel: FavouriteViewModel by viewModels<FavouriteViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        notFound(list)
        recyclerViewFavourite.adapter = adapter
        recyclerViewFavourite.layoutManager = LinearLayoutManager(requireContext())
        adapter.setListener {
            val intent = Intent(requireContext(), InfoActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
        adapter.setIncreaseItemClickListener {
            viewModel.increaseCount(it)
        }
        adapter.setDecreaseItemClickListener {
            viewModel.decreaseCount(it)
        }
        viewModel.getAllFavourite()
        viewModel.favouriteLiveData.observe(viewLifecycleOwner, {
            notFound(it)
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun notFound(list: List<FoodData>) {
        if (list.isEmpty()) {
            binding.notFound.visibility = View.VISIBLE
        } else {
            binding.notFound.visibility = View.GONE
        }
    }
}