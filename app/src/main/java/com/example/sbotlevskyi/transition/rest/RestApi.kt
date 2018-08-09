package com.example.sbotlevskyi.transition.rest

import com.example.sbotlevskyi.transition.entity.UserRespons
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class SearchRepository(val apiService: RestApi) {

    fun searchUsers(location: String, language: String): Observable<UserRespons> {
        return apiService.search(query = "location:$location+language:$language")
    }
}

interface RestApi {

    @GET("search/users")
    fun search(@Query("q") query: String)
//               ,
//               @Query("page") page: Int,
//               @Query("per_page") perPage: Int)
            : Observable<UserRespons>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): RestApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()

            return retrofit.create(RestApi::class.java)
        }
    }
}

