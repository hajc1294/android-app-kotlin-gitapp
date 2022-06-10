package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MOwner (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("login") @Expose val login: String,
    @SerializedName("url") @Expose val url: String
)