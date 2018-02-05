package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.epitech.vlcremote.fragments.PlayerFragment
import com.epitech.vlcremote.further.replaceFragment
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.RemoteService
import com.vicpin.krealmextensions.queryFirst

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
                playerFragment.connection = connection

                remoteService.runUpdateWith(connection, {
                    error ->
                        Toast.makeText(baseContext, error.message, Toast.LENGTH_SHORT).show()
                        Log.e("RemoteService", error.message)
                })

                replaceFragment(playerFragment, R.id.remote_frag_container)
            }
        }
    }
}