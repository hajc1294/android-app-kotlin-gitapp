package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MCommit (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("sha") @Expose val sha: String,
    @SerializedName("node_id") @Expose val nodeId: String,
    @SerializedName("commit") @Expose val commit: MCommitDetail
)