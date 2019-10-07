package com.example.amconsofttestproject.utils

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxWrapper {

    companion object {
        const val DEFAULT_TIMEOUT = 10
        const val DEFAULT_RETRY_ATTEMPTS = 4L

        fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS)
        }
    }
}