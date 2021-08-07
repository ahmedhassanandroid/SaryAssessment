package com.hassan.saryassessment.flagship.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.hassan.saryassessment.core.presentation.errors.asCustomException
import com.hassan.saryassessment.core.presentation.errors.responseToCustomException
import com.hassan.saryassessment.core.presentation.status.SaryStatus
import com.hassan.saryassessment.core.presentation.status.SingleLiveEvent
import com.hassan.saryassessment.core.presentation.viewmodel.BaseViewModel
import com.hassan.saryassessment.flagship.domain.interactor.GetBannersUseCase
import com.hassan.saryassessment.flagship.domain.interactor.GetCatalogUseCase
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FlagShipViewModel @Inject constructor(
    private val getBannersUseCase: GetBannersUseCase,
    private val getCatalogUseCase: GetCatalogUseCase
): BaseViewModel() {
    private val bannersStatus by lazy {
        SingleLiveEvent<SaryStatus<List<BannerModel>>>()
    }
    private val catalogStatus by lazy {
        SingleLiveEvent<SaryStatus<List<CategoryGroupModel>>>()
    }

    fun getBanners(){
        bannersStatus.value = SaryStatus.LoadingStatus(true)
        addDisposable(getBannersUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.status)
                    bannersStatus.value = SaryStatus.SuccessStatus(it.result)
                else bannersStatus.value = SaryStatus.ErrorStatus(it.responseToCustomException())
            }, {
                bannersStatus.value = SaryStatus.ErrorStatus(it.asCustomException())
            }))
    }

    fun getCatalog(){
        catalogStatus.value = SaryStatus.LoadingStatus(false)
        addDisposable(getCatalogUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.status)
                    catalogStatus.value = SaryStatus.SuccessStatus(it.result)
                else catalogStatus.value = SaryStatus.ErrorStatus(it.responseToCustomException())
            }, {
                catalogStatus.value = SaryStatus.ErrorStatus(it.asCustomException())
            }))
    }

    fun getBannersStatus(): LiveData<SaryStatus<List<BannerModel>>> = bannersStatus
    fun getCatalogStatus(): LiveData<SaryStatus<List<CategoryGroupModel>>> = catalogStatus
}