package com.hassan.saryassessment.flagship.presentation.uimodel

import com.hassan.saryassessment.flagship.domain.model.CategoryModel

data class CategoryUIModel(
    val data: CategoryModel,
    var bgColor: Int? = null
)