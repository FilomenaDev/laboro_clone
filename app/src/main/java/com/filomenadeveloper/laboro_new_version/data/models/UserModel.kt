package com.filomenadeveloper.laboro_new_version.data.api

import com.filomenadeveloper.laboro_new_version.data.models.Messege
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    var userId:String = "",
    @SerializedName("email")
    var userEmail:String = "",
    @SerializedName("owner")
    var userOwner:Boolean = false,
    @SerializedName("first_name")
    var userFristName:String = "",
    @SerializedName("last_name")
    var userLastName:String = "",
    @SerializedName("store")
    var userStore:StoreModel = StoreModel(),
    @SerializedName("token")
    var token:String = "",
    @SerializedName("email_confirmed")
    var userConfirmedEmail:Boolean = false,
)

data class StoreModel(@SerializedName("id")
                      var storeId:String = "")
