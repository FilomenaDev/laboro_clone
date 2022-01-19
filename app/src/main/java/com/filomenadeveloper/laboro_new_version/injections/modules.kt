package com.filomenadeveloper.laboro_new_version.app.injections

import com.filomenadeveloper.laboro_new_version.ui.authentication.ViewModelAccontAuthentication
import com.filomenadeveloper.laboro_new_version.data.api.ApiClient
import com.filomenadeveloper.laboro_new_version.data.repository.Repository
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.category.ViewModelCategory
import com.filomenadeveloper.laboro_new_version.ui.splash.SplahsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    single { ApiClient.RetrofitBuider.endPoints }

    factory { Repository(apiClient = get()) }

    viewModel { SplahsViewModel() }
    viewModel { ViewModelAccontAuthentication(repository = get()) }
    viewModel { ViewModelCategory(repository = get()) }
}