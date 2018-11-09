package com.eungpang.android.kotlincoroutine

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eungpang.android.kotlincoroutine.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = UserViewModel()

        APIv1.requestUserInfo(object: OnUserInfoCallback {
            override fun onCallback(user: User) {
                userViewModel.user.set(user)
            }
        })




        val loader = Loader()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.vm, userViewModel)


//        loader.loadWithThread(this)
//        loader.loadWithAsync(this)

        threadBtn.setOnClickListener {
            loader.loadWithThread(MainActivity@this)
        }

        coroutineBtn.setOnClickListener {
            loader.loadWithCoroutine(this)
        }
    }
}
