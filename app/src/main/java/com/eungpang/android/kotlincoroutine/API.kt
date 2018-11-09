package com.eungpang.android.kotlincoroutine

import android.os.AsyncTask
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

object APIv1 {
    var age = 0

    fun requestUserInfo(callback: OnUserInfoCallback) {
        AsyncTask.execute {
            val scheduler : ScheduledExecutorService = ScheduledThreadPoolExecutor(1)

            scheduler.scheduleAtFixedRate({
                age += 1
                val user: User = User("Panki", age)
                callback.onCallback(user)
            }, 0, 1, TimeUnit.SECONDS)
        }
    }

    suspend fun fetchUserInfo() {
        GlobalScope.launch {

            delay(1000)

        }
    }
}


interface OnUserInfoCallback {
    fun onCallback(user: User)
}

data class User(val name: String, val age: Int)