package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Status
import com.epitech.vlcremote.services.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_player.view.*

/**
* Created by initerworker on 31/01/18.
*/

class PlayerFragment() : TabFragment() {

    private var status: Status? = null
    private val TAG: String = "PlayerFragment"
    var remoteService: RemoteService? = null

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
            player_fullscreen.setOnClickListener { onClickFullScreen() }
            player_volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    onChangingVolume(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // called when tracking the seekbar is started
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // called when tracking the seekbar is stopped
                }
            })
            player_position.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    onSeeking(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // called when tracking the seekbar is started
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // called when tracking the seekbar is stopped
                }
            })
            player_random.setOnClickListener { onClickRandom() }
            player_repeat.setOnClickListener { onClickRepeat() }
        }

        return view
    }

    private fun onSeeking(progress: Int) {
        remoteService!!.vlcService!!.seek(remoteService!!.connection!!.basicToken(), "$progress%")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onChangingVolume(progress: Int) {
        remoteService!!.vlcService!!.changeVolume(remoteService!!.connection!!.basicToken(), progress)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onClickFullScreen() {
        remoteService!!.vlcService!!.toggleFullscreen(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onClickBack() {
        if (remoteService == null) return
        Toast.makeText(context, "Remote Back", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.jumpPrevious(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onClickStart() {
        if (remoteService == null) return
        Toast.makeText(context, "Remote Play/Pause", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.togglePlayPause(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onClickNext() {
        if (remoteService == null) return
        Toast.makeText(context, "Remote Next", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.jumpNext(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onClickRandom() {
        remoteService!!.vlcService!!.toggleRandom(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    private fun onClickRepeat() {
        remoteService!!.vlcService!!.toggleRepeat(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Status -> status = t }, { error -> Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show() })
    }

    override fun refresh() {
        Log.d(TAG, "start refresh...")
    }
}