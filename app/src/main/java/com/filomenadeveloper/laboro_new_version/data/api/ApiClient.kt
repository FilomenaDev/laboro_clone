package com.filomenadeveloper.laboro_new_version.data.api

import com.filomenadeveloper.laboro_new_version.BuildConfig
import com.filomenadeveloper.laboro_new_version.data.api.interceptors.AuthInterceptor
import com.filomenadeveloper.laboro_new_version.data.models.CategoresModel
import com.filomenadeveloper.laboro_new_version.data.models.ProductOfModel
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiClient {

    object RetrofitBuider{
        private val logger by lazy{
            HttpLoggingInterceptor().apply {
                level = when {
                    BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                    else -> HttpLoggingInterceptor.Level.NONE
                }
            }
        }

        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.MINUTES)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(AuthInterceptor())
            .build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.ApiUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val endPoints: ApiClient = getRetrofit().create(ApiClient::class.java)
    }

    @POST("users")
    suspend fun postCreatAccontEdpoit(
        @Body body: HashMap<String, Any>
    ): UserModel

    @POST("sessions")
    suspend fun postSingAccontEdpoit(
        @Body body: HashMap<String, Any>
    ): UserModel

    @POST("categories")
    suspend fun postCreateCategory(@Header("Authorization") token:String?,
                                   @Body body: HashMap<String, Any>):CategoresModel
    @Multipart
    @POST("products")
    suspend fun postCreateProduct( @Header("Content-Type") contentType: String,
                                   @Header("Authorization") token:String?,
                                   @Part("image") body: MultipartBody):ProductOfModel

    @GET("categories")
    suspend fun  getCategories(@Header("Authorization") token:String?):MutableList<CategoresModel>
}