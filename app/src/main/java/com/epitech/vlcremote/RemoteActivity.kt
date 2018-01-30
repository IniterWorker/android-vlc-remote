package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.VLCService
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
* Created by bonett_w on 1/29/18.
*/

class RemoteActivity : AppCompatActivity() {

    private val gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return false
        }

        override fun shouldSkipField(f: FieldAttributes): Boolean {
            return f.declaredClass == RealmObject::class.java
        }
    }).create()!!

    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://192.168.1.44:8080")
            .build()

    private val vlcService: VLCService = retrofit.create(VLCService::class.java)
    private val connection: Connection = Connection()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_remote)

        connection.setBasicAuth("", "toto")

        vlcService.getVLCStatus(connection.basicAuth!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status ->
                    Log.w("Debug", status.version)
                }, {
                    error ->
                    Log.e("Error", error.message)
                })
    }
}