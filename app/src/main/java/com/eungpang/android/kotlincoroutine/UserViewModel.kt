package com.eungpang.android.kotlincoroutine

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class UserViewModel(): ViewModel() {
    var user: ObservableField<User> = ObservableField()

    init {
        user.set(User("no name", 0))
    }
}