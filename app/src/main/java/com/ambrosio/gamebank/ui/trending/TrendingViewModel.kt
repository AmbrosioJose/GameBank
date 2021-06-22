package com.ambrosio.gamebank.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.network.*
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.request.IGDBWrapper.apiJsonRequest
import com.api.igdb.utils.Endpoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*

class TrendingViewModel : ViewModel() {


    private var popularLiveData : MutableLiveData<ArrayList<VideoGame>> = MutableLiveData()

    fun getTrendingListObserver(): MutableLiveData<ArrayList<VideoGame>> {
        return popularLiveData
    }

    fun fetchTrending() {
        viewModelScope.launch(Dispatchers.IO) {
            val response: ArrayList<VideoGame> = WrapperService().fetchTrendingGames()
            print(response)
            popularLiveData.postValue(response?:ArrayList())
        }
    }

}