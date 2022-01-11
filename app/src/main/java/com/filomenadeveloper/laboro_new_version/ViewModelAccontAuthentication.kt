package com.filomenadeveloper.laboro_new_version

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.filomenadeveloper.laboro_new_version.data.api.UserModel
import com.filomenadeveloper.laboro_new_version.data.repository.Repository
import kotlinx.coroutines.Dispatchers

class ViewModelAccontAuthentication(private val repository: Repository): ViewModel() {

    fun postAccountRegister(
        firstName: String,
        email: String,
        password: String,
    ) = liveData<Resource<UserModel>>(Dispatchers.IO){
        val body: HashMap<String,Any> = hashMapOf()
        body["email"]= email
        body["first_name"]= firstName
        body["password"]= password
        emit(Resource.loading(data = null))
        try {
             repository.createAccontUserRepository(body)
        }catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}