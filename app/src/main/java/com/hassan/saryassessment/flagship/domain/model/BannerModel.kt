package com.hassan.saryassessment.flagship.domain.model


import com.google.gson.annotations.SerializedName

data class BannerModel(
    @SerializedName("branches")
    val branches: List<Int>?,
    @SerializedName("button_text")
    val buttonText: String?,
    @SerializedName("cities")
    val cities: List<Any>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("expiry_date")
    val expiryDate: String?,
    @SerializedName("expiry_status")
    val expiryStatus: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("is_available")
    val isAvailable: Boolean?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("priority")
    val priority: Int?,
    @SerializedName("promo_code")
    val promoCode: Any?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("title")
    val title: String?
)