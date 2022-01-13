package com.filomenadeveloper.laboro_new_version.data.repository

import com.filomenadeveloper.laboro_new_version.data.api.ApiClient
import com.filomenadeveloper.laboro_new_version.data.api.UserModel

class Repository(private val apiClient: ApiClient) {

    suspend fun createAccontUserRepository(body: HashMap<String,Any>):UserModel{
        return apiClient.postCreatAccontEdpoit(body)
    }

    suspend fun singLoginAccontUserRepository(body: HashMap<String,Any>):UserModel{
        return apiClient.postSingAccontEdpoit(body)
    }
}