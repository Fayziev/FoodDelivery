package uz.gita.fooddelivery.shared

import android.content.Context
import com.securepreferences.SecurePreferences
import uz.gita.fooddelivery.data.enums.StartScreenEnum
import uz.gita.fooddelivery.tools.startScreen

class LocalStorage(context: Context) {
    private val preference = SecurePreferences(context, "LocalStorage", "1234567890")

    var startScreen: StartScreenEnum
        get() = preference.getString("START_SCREEN", StartScreenEnum.LOGIN.name)!!.startScreen()
        set(startScreen) = preference.edit().putString("START_SCREEN", startScreen.name).apply()
}