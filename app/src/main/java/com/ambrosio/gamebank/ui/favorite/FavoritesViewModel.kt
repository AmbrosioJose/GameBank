package com.ambrosio.gamebank.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambrosio.gamebank.foundations.ApplicationScope
import com.ambrosio.gamebank.models.IGameModel
import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.network.WrapperService
import com.ambrosio.gamebank.utils.toggleFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class FavoritesViewModel : ViewModel() {
    @Inject
    lateinit var gameModel: IGameModel

    private var favoriteLiveData: MutableLiveData<ArrayList<VideoGame>> = MutableLiveData()

    fun getFavoriteListObserver(): MutableLiveData<ArrayList<VideoGame>> {
        return favoriteLiveData
    }

    init {
        Toothpick.inject(this, ApplicationScope.scope)
    }

    fun fetchFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            gameModel.let {
                it.retrieveFavoriteGames { games ->
                    favoriteLiveData.postValue(games)
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
                    fetchFavorites()
                }
            }
        }
    }
}