package com.example.sbotlevskyi.transition.rest

import android.util.Log

//https://m.habr.com/post/336034/

class RastManager {

    val apiService = RestApi.create()

    apiService.search(/* search params go in here */)
//    val repository = SearchRepositoryProvider.provideSearchRepository()

//    repository.searchUsers("Omsk", "Java")
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
    .subscribe ({
        result ->
        Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
    }, { error ->
        error.printStackTrace()
    })
}