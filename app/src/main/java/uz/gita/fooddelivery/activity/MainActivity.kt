package uz.gita.fooddelivery.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.fooddelivery.R
import uz.gita.fooddelivery.presentation.ui.page.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomView: ChipNavigationBar = findViewById(R.id.bottomView)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, HomePage()).commit()
        bottomView.setItemEnabled(R.id.navigation_home,true)
        bottomView.setOnItemSelectedListener {

            when (it) {
                R.id.navigation_home -> {
                    currentFragment = HomePage()
                }
                R.id.navigation_favourite -> {
                    currentFragment = FavoritePage()
                }
                R.id.navigation_menu -> {
                    currentFragment = MenuPage()
                }
                R.id.navigation_location -> {
                    currentFragment = LocationPage()
                }
                R.id.navigation_profile -> {
                    currentFragment = ProfilePage()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.nav_container, currentFragment).commit()
        }

    }
}