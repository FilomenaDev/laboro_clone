package com.filomenadeveloper.laboro_new_version.data.models

import com.google.gson.annotations.SerializedName

data class ErrorModel(@SerializedName("errors")
                      val Erros: List<Messege> = ArrayList())

data class Messege(
    @SerializedName("message")
    val description: String = ""
)