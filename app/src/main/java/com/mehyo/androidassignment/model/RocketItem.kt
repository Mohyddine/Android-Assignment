package com.mehyo.androidassignment.model


import com.google.gson.annotations.SerializedName
//RocketItem that represents a single object with its attributes inside the json response

data class RocketItem(
    @SerializedName("flickr_images") val flickrImages: List<String>,
    val name: String, // Falcon 1
    @SerializedName("first_flight") val firstFlight: String, // 2006-03-24
    val wikipedia: String, // https://en.wikipedia.org/wiki/Falcon_1
    val description: String, // The Falcon 1 was an expendable launch system privately developed and manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became the first privately-developed liquid-fuel launch vehicle to go into orbit around the Earth.
    val id: String // 5e9d0d95eda69955f709d1eb
)