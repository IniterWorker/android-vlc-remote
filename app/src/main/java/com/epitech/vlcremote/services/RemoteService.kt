package com.epitech.vlcremote.services

import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.models.Status
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.epitech.vlcremote.gson.BooleanSerializer
import com.google.gson.Gson


/**
* Created by initerworker on 02/02/18.
*/
open class RemoteService(var connection: Connection) {
    var gson: Gson
    var retrofit: Retrofit
    var vlcService: VLCService

    init {
        val b = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaredClass == RealmObject::class.java
            }
        })

        val serializer = BooleanSerializer()
        b.registerTypeAdapter(Boolean::class.java, serializer)
        b.registerTypeAdapter(Boolean::class.javaPrimitiveType, serializer)
        gson = b.create()!!

        val baseUrlConnection = "http://%s:%d".format(connection.ipaddr, connection.port)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // DEBUG Interceptor
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrlConnection)
                .build()

        vlcService = retrofit.create(VLCService::class.java)
    }
}