package com.eungpang.android.kotlincoroutine

import android.app.Activity
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class Loader {
    fun loadWithThread(activity: Activity) {
        Thread(Runnable {
            Handler(Looper.getMainLooper()).post {
                val textView = activity.findViewById<TextView>(R.id.testField)
                textView.text = "Loading ..........."
            }

            var data = loadNetworkData()

            Handler(Looper.getMainLooper()).post {
                val textView = activity.findViewById<TextView>(R.id.testField)
                textView.text = data
            }
        }).start()
    }

    fun loadWithAsync(activity: Activity) {
        val loadData = object: AsyncTask<Unit, Unit, String>() {
            override fun doInBackground(vararg params: Unit?): String {
                return loadNetworkData()
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                val textView = activity.findViewById<TextView>(R.id.testField)
                textView.text = result!!
            }

            override fun onPreExecute() {
                super.onPreExecute()
                val textView = activity.findViewById<TextView>(R.id.testField)
                textView.text = "Loading ..........."
            }
        }

        loadData.execute()
    }

    fun loadWithCoroutine(activity: Activity) {
        CoroutineScope(Dispatchers.Main).launch {
            val textView = activity.findViewById<TextView>(R.id.testField)
            textView.text = "Loading ..........."

            var data = ""
//            CoroutineScope(Dispatchers.Default).async {
//                data = loadNetworkData()
//            }.await()

            data = loadNetworkData()

            textView.text = data
        }
    }




    private fun loadNetworkData(): String {
        Thread.sleep(2000)

        val str = StringBuilder()
        for (i in 0.. 100) {
            str.append("$i, ")
        }

        return str.toString()
    }
}




