package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.epitech.vlcremote.fragments.PlayerFragment
import com.epitech.vlcremote.further.replaceFragment
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.VLCService
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    private var retrofit: Retrofit? = null

    private var vlcService: VLCService? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_remote)

        setTitle("Search")

        if (intent.extras != null) {
            val connection: Connection? = queryFirst<Connection> { equalTo("id", intent.extras.getInt("id"))  }

            if (connection != null) {

                val baseUrlConnection = "http://%s:%d".format(connection.ipaddr, connection.port)

                val interceptor = HttpLoggingInterceptor()

                interceptor.level = HttpLoggingInterceptor.Level.HEADERS
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                setTitle(connection.name)

                // TODO : remove and clean
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

                vlcService = retrofit!!.create(VLCService::class.java)

                vlcService!!.getVLCStatus(connection.basicToken())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            status ->
                            Log.w("Debug", status.version)
                        }, {
                            error ->
                            Log.e("Error", error.message)
                        })

                replaceFragment(PlayerFragment.newInstance(), R.id.frag_remote_ctn)
            }
        }


    }
}