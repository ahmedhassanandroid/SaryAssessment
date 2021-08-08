package com.hassan.saryassessment.flagship.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hassan.saryassessment.R
import com.hassan.saryassessment.core.presentation.errors.handleCustomError
import com.hassan.saryassessment.core.presentation.status.SaryStatus
import com.hassan.saryassessment.core.presentation.utils.showToast
import com.hassan.saryassessment.core.presentation.view.BaseActivity
import com.hassan.saryassessment.core.presentation.viewmodel.ViewModelFactory
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import com.hassan.saryassessment.flagship.domain.model.UIType
import com.hassan.saryassessment.flagship.presentation.view.adapter.BannersAdapter
import com.hassan.saryassessment.flagship.presentation.view.adapter.CategoryGroupAdapter
import com.hassan.saryassessment.flagship.presentation.viewmodel.FlagShipViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FlagShipActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<FlagShipViewModel>
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FlagShipViewModel::class.java)
    }

    private var groupsAdapter: CategoryGroupAdapter? = null
    private var bannersAdapter: BannersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fetchData()
        observeViewModel()
        setupRecycler()
    }

    private fun fetchData(){
        viewModel.getBanners()
        viewModel.getCatalog()
    }

    private fun observeViewModel(){
        viewModel.getBannersStatus().observe(this, Observer {
            when(it){
                is SaryStatus.SuccessStatus<List<BannerModel>> -> {
                    bannersAdapter?.addItems(it.data)
                }
                is SaryStatus.ErrorStatus -> handleCustomError(it.error)
                is SaryStatus.LoadingStatus -> {
                    showLoadingProgress(true)
                }
            }
            if (it !is SaryStatus.LoadingStatus)
                showLoadingProgress(false)
        })

        viewModel.getCatalogStatus().observe(this, Observer {
            when(it){
                is SaryStatus.SuccessStatus<List<CategoryGroupModel>> -> {
                    groupsAdapter?.addItems(it.data)
                }
                is SaryStatus.ErrorStatus -> handleCustomError(it.error)
                is SaryStatus.LoadingStatus -> {
                    showLoadingProgress(true)
                }
            }
            if (it !is SaryStatus.LoadingStatus)
                showLoadingProgress(false)
        })
    }

    private fun setupRecycler(){
        groupsAdapter = CategoryGroupAdapter(arrayListOf())
        rvGroups.adapter = groupsAdapter

        bannersAdapter = BannersAdapter(arrayListOf()){
            it.link?.let { link ->
                showToast(link)
            }
        }
        bannersAdapter?.let {
            bannersSliders.setSliderAdapter(it)
        }
        bannersSliders.setInfiniteAdapterEnabled(false);
    }

    private fun showLoadingProgress(show: Boolean){
        if (show)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE
    }
}