package com.mehyo.androidassignment

import com.mehyo.androidassignment.network.SpaceXAPI
import com.mehyo.androidassignment.repository.LaunchesRepository
import com.mehyo.androidassignment.repository.RocketRepository
import com.mehyo.androidassignment.ui.pages.inner.InnerPageViewModel
import com.mehyo.androidassignment.ui.pages.main.MainPageViewModel
import com.mehyo.androidassignment.util.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//all network modules
val networkModule = module {
    //creating network api using retrofit variable
    fun createNetworkApi(retrofit: Retrofit) = retrofit.create(SpaceXAPI::class.java)

    //Building retrofit variable
    fun retrofitBuilder() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    single { createNetworkApi(retrofit = get()) }
    single { retrofitBuilder() }
}

//all repository modules
val repositoryModule = module {
    single { RocketRepository(api = get()) }
    single { LaunchesRepository(api = get()) }
}

//all viewModel modules
val viewModelModule = module {
    viewModel { InnerPageViewModel(rocketRepo = get()) }
    viewModel { MainPageViewModel(launchesRepo = get()) }
}