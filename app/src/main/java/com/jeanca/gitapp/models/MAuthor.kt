package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MAuthor (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("date") @Expose val date: String
)