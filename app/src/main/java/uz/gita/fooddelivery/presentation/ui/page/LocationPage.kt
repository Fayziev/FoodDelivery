package uz.gita.fooddelivery.presentation.ui.page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ahmadhamwi.tabsync.TabbedListMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.data.LocationData
import uz.gita.fooddelivery.databinding.PageLocationBinding
import uz.gita.fooddelivery.presentation.ui.adapter.LocationAdapter
import uz.gita.fooddelivery.presentation.viewmodel.LocationViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.LocationViewModelImpl
import uz.gita.fooddelivery.tools.scope

@AndroidEntryPoint
class LocationPage : Fragment(R.layout.page_location) {

    private val binding by viewBinding(PageLocationBinding::bind)
    private val list = ArrayList<LocationData>()
    private val adapter = LocationAdapter(list)
    private lateinit var viewModel: LocationViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        viewModel = ViewModelProvider(this@LocationPage)[LocationViewModelImpl::class.java]

        adapter.setListener {
            val locationAddress = "google.navigation:q=${it.latitude},${it.longitude}"
            val gameUri = Uri.parse(locationAddress)
            val mapIntent = Intent(Intent.ACTION_VIEW, gameUri)
            startActivity(mapIntent)
//            val intent = Intent(requireContext(), MapActivity::class.java)
//            intent.putExtra("data", it)
//            startActivity(intent)
        }
        recyclerViewLocation.adapter = adapter
        recyclerViewLocation.layoutManager = LinearLayoutManager(requireContext())
//        val mediator=TabbedListMediator(
//            recyclerViewLocation,
//            tabLayout,
//
//        )
        viewModel.getAllLocationData()
        viewModel.allLocationData.observe(viewLifecycleOwner, {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })



    }
}