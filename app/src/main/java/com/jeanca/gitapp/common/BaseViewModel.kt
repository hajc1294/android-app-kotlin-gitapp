package com.jeanca.gitapp.common

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel (
    val compositeDisposable: CompositeDisposable =  CompositeDisposable()
)