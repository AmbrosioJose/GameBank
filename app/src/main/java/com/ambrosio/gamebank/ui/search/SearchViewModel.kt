package com.ambrosio.gamebank.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.network.WrapperService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private var searchLiveData : MutableLiveData<ArrayList<VideoGame>> = MutableLiveData()

    fun getSearchListObserver(): MutableLiveData<ArrayList<VideoGame>> {
        return searchLiveData
    }

    fun fetchSearchTerm(term: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: ArrayList<VideoGame> = WrapperService().searchGames(term)
            print(response)
            searchLiveData.postValue(response?:ArrayList())
        }
    }
}