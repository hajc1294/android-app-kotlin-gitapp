package com.jeanca.gitapp.api

import com.jeanca.gitapp.models.MRepositoriesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") user: String
    ): Observable<MRepositoriesList>
}