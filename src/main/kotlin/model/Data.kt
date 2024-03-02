package model

import kotlinx.serialization.Serializable

@Serializable
data class Response(val data: Data)

@Serializable
data class Data(val couriers: List<Courier>)

@Serializable
data class Courier(
    val courierCode: String?,
    val courierName: String?,
    val website: String?,
    val isPost: Boolean?,
    val countryCode: String?,
    val requiredFields: String?,
)
