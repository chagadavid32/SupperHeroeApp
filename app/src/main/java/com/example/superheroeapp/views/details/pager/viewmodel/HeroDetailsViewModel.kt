package com.example.superheroeapp.views.details.pager.viewmodel

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superheroeapp.constants.WebConstants
import com.example.superheroeapp.data.comics.JsonComic
import com.example.superheroeapp.data.events.JsonEvents
import com.example.superheroeapp.data.heroes.HeroesData
import com.example.superheroeapp.data.series.JsonSeries
import com.example.superheroeapp.web.builder.RetrofitInstance
import com.example.superheroeapp.web.builder.clients.HeroComicsApiClient
import com.example.superheroeapp.web.builder.clients.HeroSeriesClient
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Parcelize
class HeroDetailsViewModel ( var heroeDetails: HeroesData ) : ViewModel(), Parcelable, CoroutineScope {

    private val TAG = "HeroDetailsViewModel"
    private val job: Job = Job()

    //Initial Data Hero
    private val _heroeDetail = MutableLiveData<HeroesData>()
    val heroeDetail : LiveData<HeroesData>
        get() = _heroeDetail

    //Comic Data Hero
    private val _heroComics = MutableLiveData<JsonComic>()
    val heroComics : LiveData<JsonComic>
        get() = _heroComics
    private val _heroComicsDownloaded = MutableLiveData<Boolean>()
    val heroComicsDownloaded : LiveData<Boolean>
        get() = _heroComicsDownloaded

    //Series Data Hero
    private val _heroSeries = MutableLiveData<JsonSeries>()
    val heroSeries : LiveData<JsonSeries>
        get() = _heroSeries
    private val _heroSeriesDownloaded = MutableLiveData<Boolean>()
    val heroSeriesDownloaded: LiveData<Boolean>
        get() = _heroSeriesDownloaded

    //Events Data Hero
    private val _heroEvents = MutableLiveData<JsonEvents>()
    val heroEvents: LiveData<JsonEvents>
        get() = _heroEvents
    private val _heroEventsDownloaded = MutableLiveData<Boolean>()
    val heroEventsDownloaded: LiveData<Boolean>
        get() = _heroEventsDownloaded

    init {
        _heroeDetail.value = heroeDetails
        _heroComicsDownloaded.value = false
        _heroSeriesDownloaded.value = false
        _heroEventsDownloaded.value = false
        Log.i(TAG, "Hero: ${_heroeDetail.value?.name}")
    }

    fun getComics(){
        if(!_heroComicsDownloaded.value!!){
            Log.i(TAG, "getComics invoked!")
            launch {
                val heroComicsApiClient: HeroComicsApiClient = RetrofitInstance.Builder()
                    .url(_heroeDetail.value?.comics?.collectionURI!!)
                    .buildComics()

                val getHeroComicsDataDeferred = heroComicsApiClient.getHeroComicsDataAsync(
                    limit = 100,
                    apikey = "5135fc6b46d462632d3d010484f2ae0e",
                    ts = 1,
                    hash = "939757951a464d548ec4b4c9ad7103e5",
                    url = _heroeDetail.value?.comics?.collectionURI!!
                )

                try {

                    val listHeroesComicResult = getHeroComicsDataDeferred.await()
                    if (listHeroesComicResult.isSuccessful){
                        Log.i(TAG, "Result successful")
                        _heroComics.value = listHeroesComicResult.body()
                        _heroComicsDownloaded.value = true
                    }else{
                        Log.i(TAG, "Result unsuccessful")
                    }

                }catch (exception: Exception){
                    Log.i(TAG, exception.message!!)
                }

            }
        }
    }

    fun getEvents(){
        if(!_heroEventsDownloaded.value!!) {
            Log.i(TAG, "getEvents invoked!")
            launch {
                val heroEventsApiClient = RetrofitInstance.Builder()
                    .url(_heroeDetail.value?.events?.collectionURI!!)
                    .buildEvents()

                val getHeroEventsDataDeferred = heroEventsApiClient.getHeroesDataAsync(
                    limit = 100,
                    apikey = "5135fc6b46d462632d3d010484f2ae0e",
                    ts = 1,
                    hash = "939757951a464d548ec4b4c9ad7103e5",
                    url = _heroeDetail.value?.events?.collectionURI!!
                )

                try {

                    val listHeroEventsResult = getHeroEventsDataDeferred.await()
                    if (listHeroEventsResult.isSuccessful){
                        _heroEvents.value = listHeroEventsResult.body()
                        _heroEventsDownloaded.value = true
                    }else{
                        Log.i(TAG, "Result events unsuccessful")
                    }

                }catch (exception: Exception){
                    Log.i(TAG, exception.message!!)
                }
            }
        }
    }

    fun getSeries(){
        if (!_heroSeriesDownloaded.value!!){
            Log.i(TAG, "getSeries invoked!")
            launch {
                val heroSeriesClient: HeroSeriesClient = RetrofitInstance.Builder()
                    .url(_heroeDetail.value?.series?.collectionURI!!)
                    .buildSereies()

                val getSeriesDataDeferred = heroSeriesClient.getHeroComicsDataAsync(
                    limit = 100,
                    apikey = "5135fc6b46d462632d3d010484f2ae0e",
                    ts = 1,
                    hash = "939757951a464d548ec4b4c9ad7103e5",
                    url = _heroeDetail.value?.series?.collectionURI!!
                )

                try {

                    val listHeroSeriesResult = getSeriesDataDeferred.await()
                    if (listHeroSeriesResult.isSuccessful){
                        Log.i(TAG, "Result series successful")
                        _heroSeries.value = listHeroSeriesResult.body()
                        _heroSeriesDownloaded.value = true
                    }else{
                        Log.i(TAG, "Result series unsuccessful")
                    }

                }catch (exception: Exception){
                    Log.i(TAG, exception.message!!)
                }

            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}