package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MCommitDetail (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("message") @Expose val message: String,
    @SerializedName("author") @Expose val author: MAuthor
)