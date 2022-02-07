package com.mehyo.androidassignment.model


import com.google.gson.annotations.SerializedName
//LaunchItem that represents a single object with its attributes inside the json response

data class LaunchItem(
    @SerializedName("static_fire_date_utc") val staticFireDateUtc: String, // 2022-01-23T21:22:00.000Z
    @SerializedName("rocket") val rocketID: String, // 5e9d0d95eda69973a809d1ec
    val success: Boolean?, // true
    val details: String?, // Axiom Mission 1 (or Ax-1) is a planned SpaceX Crew Dragon mission to the International Space Station (ISS), operated by SpaceX on behalf of Axiom Space. The flight will launch no earlier than 31 March 2022 and send four people to the ISS for an eight-day stay
    @SerializedName("flight_number") val flightNumber: Int, // 160
    val name: String, // SARah 1
    val upcoming: Boolean?, // true

)