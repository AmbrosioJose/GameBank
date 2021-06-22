package com.ambrosio.gamebank.network

import com.ambrosio.gamebank.models.VideoGame
import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.apicalypse.Sort
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.utils.Endpoints
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WrapperService {

     fun fetchTrendingGames(): ArrayList<VideoGame>{
        IGDBWrapper.setCredentials(CLIENT_ID, TOKEN)
        val response: String = IGDBWrapper.apiJsonRequest(
            endpoint = Endpoints.GAMES,
            query = "fields name,rating,genres.name,cover.url,first_release_date;\r\nsort first_release_date desc;\r\nwhere rating >= 0 & artworks!=null & cover!=null & genres != null;\r\n limit 33;"
        )
         println(response)

         val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(response)
    }

    fun searchGames(searchTerm: String): ArrayList<VideoGame>{
        IGDBWrapper.setCredentials(CLIENT_ID, TOKEN)

        val response: String = IGDBWrapper.apiJsonRequest(
            endpoint = Endpoints.GAMES,
            query = "search \"$searchTerm\"; fields id,name,rating,cover.url, genres.name; limit 33;"
        )
        println(response)

        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(response)
    }
}