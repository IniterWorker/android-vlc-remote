package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.models.Status
import com.epitech.vlcremote.services.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_player.view.*

/**
* Created by initerworker on 31/01/18.
*/

class PlayerFragment() : Fragment() {

    var remoteService: RemoteService? = null
    var connection: Connection? = null
    private var status: Status? = null

    companion object {
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: ConstraintLayout = inflater!!.inflate(R.layout.fragment_player, container, false) as ConstraintLayout

        // TODO: write all user interface
        with(view) {
            player_left_arrow.setOnClickListener { onClickBack() }
            player_play.setOnClickListener { onClickStart() }
            player_right_arrow.setOnClickListener { onClickNext() }
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
        if (connection == null) return
        Toast.makeText(context, "Remote Back", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.jumpPrevious(connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
        // TODO : Service back
    }

    private fun onClickStart() {
        if (connection == null) return
        Toast.makeText(context, "Remote Play/Pause", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.togglePlayPause(connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
        // TODO : Service toggle Start / Stop
    }

    private fun onClickNext() {
        if (connection == null) return
        Toast.makeText(context, "Remote Next", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.jumpNext(connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
        // TODO : Service next
    }

    fun updateStatus(status: Status) {
        // TODO : update relative DataStatus
    }
}