package com.greenv.feb14.response

data class RamResponse(
    val info: Info,
    val listResults: List<ResultObject>,
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
)
