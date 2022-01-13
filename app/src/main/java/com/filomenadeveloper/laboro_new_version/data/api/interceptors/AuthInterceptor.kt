package com.filomenadeveloper.laboro_new_version.data.api.interceptors


import com.filomenadeveloper.laboro_new_version.data.api.UserModel
import com.filomenadeveloper.laboro_new_version.database.DatabaseKeys
import com.filomenadeveloper.laboro_new_version.database.HawkStorage
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val response = chain.proceed(requestBuilder.build())

        return response
    }

    private fun saveOnLocalDatabase(account: UserModel) {
        HawkStorage().putData(DatabaseKeys.id, account.userId)
        HawkStorage().putData(DatabaseKeys.email, account.userEmail)
        HawkStorage().putData(DatabaseKeys.firstName, account.userFristName)
        HawkStorage().putData(DatabaseKeys.owner, account.userOwner )
        HawkStorage().putData(DatabaseKeys.token, account.token)


    }
}