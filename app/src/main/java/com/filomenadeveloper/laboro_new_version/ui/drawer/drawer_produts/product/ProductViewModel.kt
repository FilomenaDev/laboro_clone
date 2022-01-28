package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.content.ContentResolver
import android.net.Uri
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.filomenadeveloper.laboro_new_version.Resource
import com.filomenadeveloper.laboro_new_version.data.models.ProductOfModel
import com.filomenadeveloper.laboro_new_version.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import okhttp3.RequestBody
import okhttp3.MultipartBody





class ProductViewModel(private val repository: Repository): ViewModel() {

    fun postProducts(
        name: String,
        price:String,
        description:String,
        promotionPrice: Double,
        salePerUnit: Boolean,
        highlight:Boolean,
        avaliable:Boolean,
        idCategory:Int,
        stock:Boolean,
        quantity:Int,
        minino:Int,
        image: ByteArray ,
        ) = liveData<Resource<ProductOfModel>>(Dispatchers.IO){

        val fileBody: RequestBody =
            RequestBody.create("image/*".toMediaTypeOrNull(), image)

        val body: MultipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("name", name.toString())
            .addFormDataPart("price", price.toString())
            .addFormDataPart("image", "name", fileBody)
            .build()

        repository.createProducts(body);


        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.createProducts(body)))
        }catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

  @NonNull
  fun createPartFromString(string: String?): RequestBody {
        return RequestBody.create(okhttp3.MultipartBody.FORM,string.toString())
    }
}