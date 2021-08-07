package com.hassan.saryassessment.flagship.domain.interactor

import com.hassan.saryassessment.core.data.entity.ApiResponse
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.hassan.saryassessment.flagship.domain.repository.FlagShipRepository
import io.reactivex.Single
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
    private val flagShipRepository: FlagShipRepository
){
    fun execute(): Single<ApiResponse<List<BannerModel>>> {
        return flagShipRepository.getBanners()
    }
}