package com.hassan.saryassessment.flagship.domain.model


import com.google.gson.annotations.SerializedName

data class CategoryGroupModel(
    @SerializedName("data_type")
    val dataType: DataType,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("row_count")
    val rowCount: Int = 1,
    @SerializedName("show_more_enabled")
    val showMoreEnabled: Boolean?,
    @SerializedName("show_title")
    val showTitle: Boolean?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("ui_type")
    var uiType: UIType,
    @SerializedName("data")
    val data: List<CategoryModel>
)

enum class DataType{
    @SerializedName("smart")
    SMART,
    @SerializedName("group")
    GROUP,
    @SerializedName("banner")
    BANNER
}

enum class UIType{
    @SerializedName("grid")
    GRID,
    @SerializedName("linear")
    LINEAR,
    @SerializedName("slider")
    SLIDER
}