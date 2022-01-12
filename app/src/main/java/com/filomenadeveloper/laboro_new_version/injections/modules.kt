package com.filomenadeveloper.laboro_new_version.app.injections

import com.filomenadeveloper.laboro_new_version.ViewModelAccontAuthentication
import com.filomenadeveloper.laboro_new_version.data.api.ApiClient
import com.filomenadeveloper.laboro_new_version.data.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    single { ApiClient.RetrofitBuider.endPoints }

    factory { Repository(apiClient = get()) }

    viewModel { ViewModelAccontAuthentication(repository = get()) }
}