package com.ambrosio.gamebank.ui.trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.gamebank.foundations.ApplicationScope
import com.ambrosio.gamebank.models.IGameModel
import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.network.*
import com.ambrosio.gamebank.utils.toggleFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class TrendingViewModel : ViewModel() {


    @Inject
    lateinit var gameModel: IGameModel

    private var popularLiveData: MutableLiveData<ArrayList<VideoGame>> = MutableLiveData()

    fun getTrendingListObserver(): MutableLiveData<ArrayList<VideoGame>> {
        return popularLiveData
    }

    init {
        Toothpick.inject(this, ApplicationScope.scope)
    }

    fun fetchTrending() {
        viewModelScope.launch(Dispatchers.IO) {
            gameModel.let {
                it.retrieveGames { games ->
                    if (games.isNullOrEmpty()) {
                        val response: ArrayList<VideoGame> = WrapperService().fetchTrendingGames()
                        saveGamesToDbThenPost(response)
                    } else {
                        popularLiveData.postValue(games)
                    }
                }
            }
        }
    }

    private fun saveGamesToDbThenPost(games: ArrayList<VideoGame>) {
        viewModelScope.launch(Dispatchers.IO) {
            gameModel.let {
                it.addGames(games) { isSuccess ->
                    println("Games save $isSuccess")
                    popularLiveData.postValue(games)
                }
            }
        }
    }

    fun toggleFavAndUpdate(game: VideoGame){
        toggleFavorite(game)

        viewModelScope.launch(Dispatchers.IO) {
            gameModel.let {
                it.updateGame(game){ isSuccess ->
                    println("Games updated $isSuccess")
                    fetchTrending()
                }
            }
        }
    }



}