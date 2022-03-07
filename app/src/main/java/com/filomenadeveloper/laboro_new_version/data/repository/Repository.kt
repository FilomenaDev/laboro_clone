package com.filomenadeveloper.laboro_new_version.data.repository

import com.filomenadeveloper.laboro_new_version.data.api.ApiClient
import com.filomenadeveloper.laboro_new_version.data.api.UserModel
import com.filomenadeveloper.laboro_new_version.data.models.CategoresModel
import com.filomenadeveloper.laboro_new_version.data.models.ProductOfModel
import com.filomenadeveloper.laboro_new_version.database.DatabaseKeys
import com.filomenadeveloper.laboro_new_version.database.HawkStorage
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Repository(private val apiClient: ApiClient) {

    suspend fun createAccontUserRepository(body: HashMap<String,Any>):UserModel{
        return apiClient.postCreatAccontEdpoit(body)
    }

    suspend fun singLoginAccontUserRepository(body: HashMap<String,Any>):UserModel{
        return apiClient.postSingAccontEdpoit(body)
    }
    suspend fun createCategories(body: HashMap<String, Any>):CategoresModel{
        return apiClient.postCreateCategory("Token ${HawkStorage().getData("idTokenKey").toString()}",body)
    }

    suspend fun createProducts( body: RequestBody):ProductOfModel{
        return apiClient.postCreateProduct("multipart/form-data","Token ${HawkStorage().getData("idTokenKey").toString()}", body)
    }
    suspend fun ListOfCategories(): MutableList<CategoresModel>{
        return apiClient.getCategories("Token ${HawkStorage().getData("idTokenKey").toString()}")
    }
}