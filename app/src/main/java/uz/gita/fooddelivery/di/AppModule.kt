package uz.gita.fooddelivery.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.fooddelivery.app.App
import uz.gita.fooddelivery.database.AppDatabase
import uz.gita.fooddelivery.shared.LocalStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getLocalStorage() = LocalStorage(App.instance)

    @Provides
    @Singleton
    fun getFirebaseDatabase() = Firebase.firestore

    @Provides
    @Singleton
    fun getDatabase()=AppDatabase.getDatabase().getDao()

}