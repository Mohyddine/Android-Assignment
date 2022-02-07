package com.mehyo.androidassignment.network

import com.mehyo.androidassignment.model.LaunchItem
import com.mehyo.androidassignment.model.RocketItem
import com.mehyo.androidassignment.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXAPI {

    //suspending functions for GET network calls

    //getting all Launches
    @GET(Constants.LAUNCHES)
    suspend fun getLaunches(): Response<List<LaunchItem>>
    //getting Rocket details by ID
    @GET(Constants.ROCKET)
    suspend fun getRocketByID( @Path(value = "id") rocketId: String): Response<RocketItem>
}