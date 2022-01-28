package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.filomenadeveloper.laboro_new_version.Resource

import com.filomenadeveloper.laboro_new_version.data.models.CategoresModel
import com.filomenadeveloper.laboro_new_version.data.repository.Repository
import kotlinx.coroutines.Dispatchers

class ViewModelCategory(private val repository: Repository): ViewModel() {

    fun postCategories(name: String)= liveData<Resource<CategoresModel>>(Dispatchers.IO){
        val body: HashMap<String,Any> = hashMapOf()
        body["name"]= name
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.createCategories(body)))
        }catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getCategories()= liveData<Resource<MutableList<CategoresModel>>>(Dispatchers.IO){
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = repository.ListOfCategories()))
        }catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}