package com.example.workmanagerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = Data.Builder().putInt("intKey",1).build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .build()

        /*

        val myWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this@MainActivity).enqueue(myWorkRequest)

         */


        /*
        val myWorkRequest : WorkRequest = PeriodicWorkRequestBuilder<RefreshDatabase >(15,TimeUnit.MINUTES)
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this@MainActivity).enqueue(myWorkRequest)

       WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this,
           Observer {
               if (it.state == WorkInfo.State.RUNNING){
                   println("running")

               }else if (it.state == WorkInfo.State.FAILED){
                   println("failed")
               }else if (it.state == WorkInfo.State.SUCCEEDED){
                   println("succeded")
               }
           })
        //WorkManager.getInstance(this).cancelAllWork()


         */
        val myWorkRequest : OneTimeWorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this@MainActivity).beginWith(myWorkRequest)
            .then(myWorkRequest)
            .enqueue()



    }
}