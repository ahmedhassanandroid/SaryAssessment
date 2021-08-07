package com.hassan.saryassessment.flagship.domain.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("cover")
    val cover: Any?,
    @SerializedName("group_id")
    val groupId: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?
)