package com.hassan.saryassessment.flagship.data.datasource.remote

import com.hassan.saryassessment.core.data.entity.ApiResponse
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import io.reactivex.Single
import retrofit2.http.GET

interface FlagShipAPI {
    @GET("baskets/76097/banners/")
    fun getBanners(): Single<ApiResponse<List<BannerModel>>>

    @GET("baskets/76097/catalog/")
    fun getCatalog(): Single<ApiResponse<List<CategoryGroupModel>>>
}