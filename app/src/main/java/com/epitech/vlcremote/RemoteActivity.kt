package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.epitech.vlcremote.fragments.PlayerFragment
import com.epitech.vlcremote.further.replaceFragment
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.RemoteService
import com.vicpin.krealmextensions.queryFirst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
* Created by bonett_w on 1/29/18.
*/

class RemoteActivity : AppCompatActivity() {

    lateinit var remoteService: RemoteService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_remote)

        title = "Search"

        if (intent.extras != null) {
            val connection: Connection? = queryFirst<Connection> { equalTo("id", intent.extras.getInt("id"))  }

            if (connection != null) {
                remoteService = RemoteService(connection.ipaddr!!, connection.port)

                remoteService.vlcService!!.getVLCStatus(connection.basicToken())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            status -> Log.w("Debug", status.version)
                        }, {
                            error -> Log.e("Error", error.message)
                        })

                replaceFragment(PlayerFragment.newInstance(), R.id.frag_remote_ctn)
            }
        }
    }
}