package com.ambrosio.gamebank.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.network.CLIENT_ID
import com.ambrosio.gamebank.network.RetroInstance
import com.ambrosio.gamebank.network.Service
import com.ambrosio.gamebank.network.TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Trending Fragment"
    }

    private var popularLiveData : MutableLiveData<ArrayList<VideoGame>> = MutableLiveData()
    val text: LiveData<String> = _text

    fun fetchPopular() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(Service::class.java)
            val response  = retroInstance.fetchGames(
                clientId = CLIENT_ID,
                authorization = TOKEN
                )
            popularLiveData.postValue(response?.videoGames?:ArrayList())
        }
    }
}