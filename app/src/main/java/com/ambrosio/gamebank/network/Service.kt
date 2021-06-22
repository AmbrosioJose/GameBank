package com.ambrosio.gamebank.network

import com.ambrosio.gamebank.models.VideoGame
import com.ambrosio.gamebank.models.VideoGamesResponse
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.utils.Endpoints
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Header

interface Service {
    @POST("games/")
    suspend fun fetchGames(
        @Header("client-id") clientId: String,
        @Header("Authorization") authorization: String,
        @Header("Content-Type") type: String = "text/plain",
        @Header("x-user-agent") agent: String = "igdb-api-jvm",
        @Body() body: String = "fields name,rating,genres.name,cover.url,first_release_date; sort first_release_date desc; where total_rating >= 0;",
    ): ArrayList<VideoGame>

    suspend fun fetchTrendingGames(){
        IGDBWrapper.setCredentials(CLIENT_ID, TOKEN)
        val response: String = IGDBWrapper.apiJsonRequest(
            endpoint = Endpoints.GAMES,
            query = "fields name,rating,genres.name,cover.url,first_release_date;\r\nsort first_release_date desc;\r\nwhere total_rating >= 0;\r\n"
        )
    }
}