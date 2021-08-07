package com.hassan.saryassessment.flagship.domain.repository

import com.hassan.saryassessment.core.data.entity.ApiResponse
import com.hassan.saryassessment.flagship.data.datasource.remote.FlagShipAPI
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import io.reactivex.Single
import javax.inject.Inject

interface FlagShipRepository {
    fun getBanners(): Single<ApiResponse<List<BannerModel>>>

    fun getCatalog(): Single<ApiResponse<List<CategoryGroupModel>>>
}