package com.jeanca.gitapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeanca.gitapp.api.ApiProvider
import com.jeanca.gitapp.common.BaseViewModel
import com.jeanca.gitapp.common.enums.Status
import com.jeanca.gitapp.models.MRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataViewModel: BaseViewModel() {

    private var status: MutableLiveData<Status> = MutableLiveData()
    private var data: MutableLiveData<List<MRepository>> = MutableLiveData()

    fun getData(): LiveData<List<MRepository>> = data

    fun getEpisodes() {
        status.value = Status.LOADING
        compositeDisposable.add(ApiProvider.provider("token")
            .getRepositories("user:user")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data.value = it.items
                status.value = Status.DONE
            }, {
                status.value = Status.ERROR
                it.printStackTrace()
            })
        )
    }
}