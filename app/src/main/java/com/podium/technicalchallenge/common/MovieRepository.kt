package com.podium.technicalchallenge.common

import com.google.gson.Gson
import com.podium.technicalchallenge.common.network.queries.Queries
import com.podium.technicalchallenge.common.network.retrofit.GraphQLService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val gson: Gson,
                                          private val graphQLService: GraphQLService) {

    suspend fun getMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO){
            val paramObject = JSONObject()
            paramObject.put("query", Queries.getMoviesQuery())

            val response = graphQLService.query(paramObject.toString())
            val jsonBody = response.body()
            val data = gson.fromJson(jsonBody, MovieResponse::class.java)
            data.data.movies
        }
    }
}