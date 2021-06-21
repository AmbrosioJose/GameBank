package com.ambrosio.gamebank.models

import kotlinx.serialization.Serializable

@Serializable
class VideoGamesResponse {
    var videoGames: ArrayList<VideoGame>? = ArrayList()
}