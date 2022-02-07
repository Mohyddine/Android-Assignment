package com.mehyo.androidassignment.repository

import com.mehyo.androidassignment.network.SpaceXAPI

class LaunchesRepository(private val api: SpaceXAPI) {
    //getting all Launches using the api
    suspend fun getLaunches() = api.getLaunches()

}