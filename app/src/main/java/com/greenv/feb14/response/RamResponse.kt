package com.greenv.feb14.response

data class RamResponse(
    val info: Info,
    val results: List<ResultObject>,
)

data class Info(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: String? = null,
)

data class ResultObject(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

data class Location(
    val name: String,
    val url: String,
)

data class Origin(
    val name: String,
    val url: String,
)
