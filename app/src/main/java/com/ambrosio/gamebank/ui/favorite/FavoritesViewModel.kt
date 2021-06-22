package com.ambrosio.gamebank.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.network.WrapperService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private var searchLiveData : MutableLiveData<ArrayList<VideoGame>> = MutableLiveData()

    fun getSearchListObserver(): MutableLiveData<ArrayList<VideoGame>> {
        return searchLiveData
    }

    fun fetchTrending(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: ArrayList<VideoGame> = WrapperService().searchGames(searchTerm)
            print(response)
            searchLiveData.postValue(response?:ArrayList())
        }
    }
}