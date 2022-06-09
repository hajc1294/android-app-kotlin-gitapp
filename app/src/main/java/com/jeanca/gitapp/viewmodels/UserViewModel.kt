package com.jeanca.gitapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeanca.gitapp.api.ApiProvider
import com.jeanca.gitapp.common.BaseViewModel
import com.jeanca.gitapp.common.enums.Status
import com.jeanca.gitapp.models.MRepository
import com.jeanca.gitapp.models.MUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel: BaseViewModel() {

    private var status: MutableLiveData<Status> = MutableLiveData()
    private var user: MutableLiveData<MUser> = MutableLiveData()

    /**
     *
     */
    fun getData(): LiveData<MUser> = user

    /**
     *
     */
    fun getUserInfo(username: String, token: String) {
        status.value = Status.LOADING
        compositeDisposable.add(
            ApiProvider.provider(token)
            .getUserInfo(username)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.value = it
                status.value = Status.DONE
            }, {
                status.value = Status.ERROR
                it.printStackTrace()
            })
        )
    }
}