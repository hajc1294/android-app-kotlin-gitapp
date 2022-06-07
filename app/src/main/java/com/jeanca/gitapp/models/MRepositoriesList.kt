package com.jeanca.gitapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MRepositoriesList (
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("total_count") @Expose val totalCount: Int,
    @SerializedName("incomplete_results") @Expose val incompleteResults: Boolean,
    @SerializedName("items") @Expose val items: List<MRepository>,
)