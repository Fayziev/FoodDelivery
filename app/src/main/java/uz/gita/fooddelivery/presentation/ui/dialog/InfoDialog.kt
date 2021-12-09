package uz.gita.fooddelivery.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.data.LocationData

class InfoDialog : DialogFragment(R.layout.dialog_info) {
    private lateinit var locationData: LocationData
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            locationData = it.getSerializable("data") as LocationData
        }
        view.findViewById<TextView>(R.id.textLocation).text = locationData.name
    }
}