package uz.gita.fooddelivery.presentation.ui.page

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.activity.InfoActivity
import uz.gita.fooddelivery.data.FoodData
import uz.gita.fooddelivery.databinding.PageMenuBinding
import uz.gita.fooddelivery.presentation.ui.adapter.FoodsAdapter
import uz.gita.fooddelivery.presentation.viewmodel.MenuViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.MenuViewModelImpl
import uz.gita.fooddelivery.tools.scope

@AndroidEntryPoint
class MenuPage : Fragment(R.layout.page_menu) {
    private val list = ArrayList<FoodData>()
    private val adapter = FoodsAdapter(list)
    private val binding by viewBinding(PageMenuBinding::bind)
    private lateinit var viewModel: MenuViewModel
    private val tabList = ArrayList<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        viewModel = ViewModelProvider(this@MenuPage)[MenuViewModelImpl::class.java]
        recyclerViewMenu.adapter = adapter
        recyclerViewMenu.layoutManager = GridLayoutManager(
            requireContext(),
            2, LinearLayoutManager.VERTICAL, false
        )
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
        viewModel.getAllFoods()

        viewModel.categoryFoodLiveData.observe(viewLifecycleOwner, {
        })
        tabList.add("Set")
        tabList.add("Lavash")
        tabList.add("Shaurma")
        tabList.add("Donar")
        tabList.add("Burger")
        tabList.add("Xot-dog")
        tabList.add("Desertlar")
        tabList.add("Ichimliklar")
        tabList.add("Gazaklar")

        viewModel.allFoodLiveData.observe(viewLifecycleOwner, {
            val sordetList = it.sortedBy { data ->
                data.type
            }
            list.clear()
            sordetList.forEach { dataaa ->
                list.add(dataaa)
            }
            adapter.notifyDataSetChanged()
        })
        tabList.forEach {
            tablayout.addTab(tablayout.newTab().setText(it))
        }
        tablayout.tabGravity = TabLayout.GRAVITY_START

        TabbedListMediator(
            recyclerViewMenu,
            tablayout,
            tabList.indices.toList(),
            true
        ).attach()
    }
}