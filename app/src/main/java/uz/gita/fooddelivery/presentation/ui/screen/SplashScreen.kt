package uz.gita.fooddelivery.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.activity.MainActivity
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.app.App
import uz.gita.fooddelivery.domain.repository.impl.AppRepositoryImpl
import uz.gita.fooddelivery.presentation.viewmodel.SplashViewModel
import uz.gita.fooddelivery.presentation.viewmodel.impl.SplashViewModelImpl
import uz.gita.fooddelivery.shared.LocalStorage

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openMainLiveData.observe(this, openMainObserver)
        AppRepositoryImpl(LocalStorage(App.instance), Firebase.firestore).getAds()
    }

    private val openMainObserver = Observer<Unit> {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}