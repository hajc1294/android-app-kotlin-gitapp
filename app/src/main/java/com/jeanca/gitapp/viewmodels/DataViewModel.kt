package com.jeanca.gitapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeanca.gitapp.api.ApiProvider
import com.jeanca.gitapp.common.BaseViewModel
import com.jeanca.gitapp.common.enums.Status
import com.jeanca.gitapp.models.MCommit
import com.jeanca.gitapp.models.MRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataViewModel: BaseViewModel() {

    private var status: MutableLiveData<Status> = MutableLiveData()
    private var repositories: MutableLiveData<List<MRepository>> = MutableLiveData()
    private var commits: MutableLiveData<List<MCommit>> = MutableLiveData()

    /**
     *
     */
    fun repositories(): LiveData<List<MRepository>> = repositories

    /**
     *
     */
    fun commits(): LiveData<List<MCommit>> = commits

    /**
     *
     */
    fun getRepositories(username: String, token: String) {
        status.value = Status.LOADING
        compositeDisposable.add(ApiProvider.provider(token)
            .getRepositories("user:${username}")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                repositories.value = it.items
                status.value = Status.DONE
            }, {
                status.value = Status.ERROR
                it.printStackTrace()
            })
        )
    }

    /**
     *
     */
    fun getCommits(username: String, repo: String, token: String) {
        status.value = Status.LOADING
        compositeDisposable.add(ApiProvider.provider(token)
            .getCommits(username, repo)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                commits.value = it
                status.value = Status.DONE
            }, {
                status.value = Status.ERROR
                it.printStackTrace()
            })
        )
    }
}