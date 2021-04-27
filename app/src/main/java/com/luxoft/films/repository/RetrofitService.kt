package com.luxoft.films.repository

import com.luxoft.films.dto.Gist
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("gists/public")
    suspend fun getGists(): Response<List<Gist>>
}