package com.hassan.saryassessment.flagship.data.repository

import com.hassan.saryassessment.core.data.entity.ApiResponse
import com.hassan.saryassessment.flagship.data.datasource.remote.FlagShipAPI
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import com.hassan.saryassessment.flagship.domain.repository.FlagShipRepository
import io.reactivex.Single
import javax.inject.Inject

class FlagShipRepositoryImpl @Inject constructor(
    private val api: FlagShipAPI
): FlagShipRepository {

    override fun getBanners(): Single<ApiResponse<List<BannerModel>>> {
        return api.getBanners()
    }

    override fun getCatalog(): Single<ApiResponse<List<CategoryGroupModel>>> {
        return api.getCatalog()
    }

}