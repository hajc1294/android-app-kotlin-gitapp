package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MUser (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("avatar_url") @Expose val avatarUrl: String,
    @SerializedName("html_url") @Expose val htmlUrl: String,
    @SerializedName("company") @Expose val company: String?,
    @SerializedName("location") @Expose val location: String?,
    @SerializedName("public_repos") @Expose val publicRepos: Int,
    @SerializedName("followers") @Expose val followers: Int,
    @SerializedName("created_at") @Expose val createdAt: String
) {
    constructor() : this(null, "", "", "", null,
        null, 0, 0, "")
}