package com.filomenadeveloper.laboro_new_version.data.models

import com.google.gson.annotations.SerializedName

data class CategoresModel (@SerializedName("id")
                           var storeId:String = "",
                           @SerializedName("name")
                           var nameCategory:String = "")