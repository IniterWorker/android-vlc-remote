package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TimeUtils
import com.epitech.vlcremote.fragments.PlayerFragment
import com.epitech.vlcremote.further.replaceFragment
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.RemoteService
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
* Created by bonett_w on 1/29/18.
*/

class RemoteActivity : AppCompatActivity() {

    lateinit var remoteService: RemoteService
    lateinit var playerFragment: PlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_remote)

        playerFragment = PlayerFragment.newInstance()

        title = "Search"

        if (intent.extras != null) {
            val connection: Connection? = queryFirst<Connection> { equalTo("id", intent.extras.getInt("id")) }
            if (connection != null) {
                // init remote service
                remoteService = RemoteService(connection.ipaddr!!, connection.port)

                // inject in fragments
                playerFragment.remoteService = remoteService

                remoteService.vlcService!!.getVLCStatus(connection.basicToken())
                        .repeatWhen {
                            t: Observable<Any> -> t.delay(2, TimeUnit.SECONDS)
                        }
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ status ->
                            Log.w("Debug", status.version)
                        }, { error ->
                            Log.e("Error", error.message)
                        })

                replaceFragment(playerFragment, R.id.frag_remote_ctn)
            }
        }
    }
}