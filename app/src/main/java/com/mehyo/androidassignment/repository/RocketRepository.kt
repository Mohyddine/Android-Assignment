package com.mehyo.androidassignment.repository

import com.mehyo.androidassignment.network.SpaceXAPI

class RocketRepository(private val api: SpaceXAPI) {

    //getting Rocket details by ID using the api
    suspend fun getRocketByID(id:String) = api.getRocketByID(id)

}