package com.epitech.vlcremote.services

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by initerworker on 02/02/18.
 */
open class RemoteService(ipaddr: String, port: Int) {
    private val gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return false
        }

        override fun shouldSkipField(f: FieldAttributes): Boolean {
            return f.declaredClass == RealmObject::class.java
        }
    }).create()!!

    private var retrofit: Retrofit? = null

    var vlcService: VLCService? = null
        private set(vlc) { field = vlc }

    init {
        val baseUrlConnection = "http://%s:%d".format(ipaddr, port)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // TODO : remove and clean
        // DEBUG Interceptor
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        // TODO : make custom retrofit builder
        retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrlConnection)
                .build()

        vlcService = retrofit!!.create(VLCService::class.java)
    }
}