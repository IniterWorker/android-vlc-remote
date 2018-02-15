package com.epitech.vlcremote.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.SeekBar
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Status
import kotlinx.android.synthetic.main.fragment_player.view.*
import kotlinx.android.synthetic.main.view_remote_controller.view.*

/**
* Created by initerworker on 11/02/18.
*/

class RemoteControllerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    var actionListener: OnRemoteControllerActionListener? = null

    init {
        inflate(context, R.layout.view_remote_controller, this)
        player_left_arrow.setOnClickListener { actionListener?.onClickBack() }
        player_left_arrow.setOnClickListener { actionListener?.onClickBack() }
        player_play.setOnClickListener { actionListener?.onClickStart() }
        player_right_arrow.setOnClickListener { actionListener?.onClickNext() }
        player_volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser)
                    actionListener?.onChangingVolume(progress)
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
                if (fromUser)
                    actionListener?.onSeeking(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // called when tracking the seekbar is started
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // called when tracking the seekbar is stopped
            }
        })
        player_random.setOnClickListener { actionListener?.onClickRandom() }
        player_repeat.setOnClickListener { actionListener?.onClickRepeat() }
        player_stop.setOnClickListener { actionListener?.onClickStop() }
        player_retweet.setOnClickListener { actionListener?.onClickLoop() }
    }

    fun injectStatus(status: Status) {
        player_tv_current_time.text = status.currentTimeFromated()
        player_tv_end_time.text = status.endTimeFormated()
        /*
        when {
            status.information!!.category!!.meta!!.title != null ->
                remote_tv_title.text = status.information.category!!.meta!!.title
            status.information!!.category!!.meta!!.filename != null ->
                remote_tv_title.text = status.information.category!!.meta!!.filename
            else ->
                remote_tv_title.text = "Unknown"
        }
        remote_tv_quick.text = ""
        if (status.information.category!!.meta!!.artist != null )
            remote_tv_quick.text = status.information.category!!.meta!!.artist
        */
        player_position.progress = (status.position!! * 100).toInt()
        var volume: Int = (status.volume!! ).toInt()
        if (volume > player_volume.max)
            volume = player_volume.max;
        player_volume.progress = volume;
    }

    interface OnRemoteControllerActionListener {
        fun onSeeking(progress: Int) {}

        fun onChangingVolume(progress: Int) {}

        fun onClickStop() {}

        fun onClickBack() {}

        fun onClickStart() {}

        fun onClickNext() {}

        fun onClickRandom() {}

        fun onClickRepeat() {}

        fun onClickLoop() {}
    }
}