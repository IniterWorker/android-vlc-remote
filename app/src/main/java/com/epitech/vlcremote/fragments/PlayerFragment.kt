package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.models.Status
import com.epitech.vlcremote.services.RemoteService
import kotlinx.android.synthetic.main.fragment_player.view.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
/**
* Created by initerworker on 31/01/18.
*/

class PlayerFragment() : Fragment() {

    lateinit var remoteService: RemoteService
    lateinit var connection: Connection
    lateinit var status: Status

    companion object {
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: LinearLayout = inflater!!.inflate(R.layout.fragment_player, container, false) as LinearLayout

        // TODO: write all user interface
        with(view) {
            remote_back.setOnClickListener { onClickBack() }
            remote_start.setOnClickListener { onClickStart() }
            remote_next.setOnClickListener { onClickNext() }
        }

        return view
    }

    private fun formatSecondsToString(seconds: Int) : String {
        var H: Int = 0
        var M: Int = 0
        var S: Int = 0

        M = seconds / 60
        S = seconds % 60
        H = M / 60
        M %= 60

        return "%2d:%2d:%2d".format(H, M, S)
    }

    private fun onClickBack() {
        Toast.makeText(context, "Remote Back", Toast.LENGTH_SHORT).show()
        // TODO : Service back
    }

    private fun onClickStart() {
        Toast.makeText(context, "Remote Play/Pause", Toast.LENGTH_SHORT).show()
        remoteService.vlcService!!.togglePlayPause(connection.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
        // TODO : Service toggle Start / Stop
    }

    private fun onClickNext() {
        Toast.makeText(context, "Remote Next", Toast.LENGTH_SHORT).show()
        // TODO : Service next
    }

    fun updateStatus(status: Status) {
        // TODO : update relative DataStatus
    }
}