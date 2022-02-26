package com.example.superheroeapp.views.heroes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superheroeapp.constants.WebConstants
import com.example.superheroeapp.data.heroes.Json
import com.example.superheroeapp.web.builder.RetrofitInstance
import com.example.superheroeapp.web.builder.clients.HeroesApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class HeroesListViewModel: ViewModel() , CoroutineScope{

    private val TAG = "HeroesListViewModel"
    private val job: Job = Job()

    private val _heroesData = MutableLiveData<Json>()
    val heroesData : LiveData<Json>
        get() = _heroesData

    init {
        getHeroesData()
    }

    private fun getHeroesData(){
        Log.i(TAG, "getHeroesData invoked!")
        launch {
            val heroesApiClient: HeroesApiClient = RetrofitInstance.Builder()
                .url(WebConstants.BASE_URL)
                .buildBase()

            val getHeroesDataDeferred = heroesApiClient.getHeroesDataAsync(
                apikey = "5135fc6b46d462632d3d010484f2ae0e",
                ts = 1,
                hash = "939757951a464d548ec4b4c9ad7103e5",
                limit = 100
            )

            try {
                val listHeroesResult = getHeroesDataDeferred.await()
                if (listHeroesResult.isSuccessful){
                    Log.i(TAG, "Result successful")
                    _heroesData.value = listHeroesResult.body()
                }else{
                    Log.i(TAG, "Result Unsuccessful")
                }
            }catch (exception: Exception){
                Log.i(TAG, exception.message!!)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}