package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MRepository (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("full_name") @Expose val fullName: String,
    @SerializedName("description") @Expose val description: String?,
    @SerializedName("created_at") @Expose val createdAt: String,
    @SerializedName("stargazers_count") @Expose val stargazersCount: Int,
    @SerializedName("language") @Expose val language: String,
    @SerializedName("forks") @Expose val forks: Int,
    @SerializedName("owner") @Expose val owner: MOwner
)