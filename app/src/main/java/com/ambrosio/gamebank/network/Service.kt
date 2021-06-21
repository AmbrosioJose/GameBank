package com.ambrosio.gamebank.network

import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.models.VideoGamesResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Header

interface Service {
    @POST("games/")
    suspend fun fetchGames(
        @Header("Client-ID") clientId: String,
        @Header("Authorization") authorization: String,
        @Body() body: String = "\"fields name,rating,genres.name,cover.url,first_release_date;\\r\\nsort first_release_date desc;\\r\\nwhere total_rating >= 0;\\r\\n\""
    ) : VideoGamesResponse?
}