package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Status
import com.epitech.vlcremote.services.RemoteService
import com.epitech.vlcremote.views.RemoteControllerView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_player.view.*
import java.util.concurrent.TimeUnit

/**
* Created by initerworker on 31/01/18.
*/

class PlayerFragment() : TabFragment(), RemoteControllerView.OnRemoteControllerActionListener {


    private val TAG: String = "PlayerFragment"
    var remoteService: RemoteService? = null
    private lateinit var statusObservable: Observable<Status>
    private lateinit var scheduler: Scheduler
    private lateinit var subscription: Disposable

    companion object {
        fun newInstance(): PlayerFragment {
            return PlayerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_player, container, false) as ConstraintLayout

        view.remote_controller.actionListener = this
        view.player_fullscreen.setOnClickListener({ onClickFullScreen() })
        view.player_subtitle.setOnClickListener({  }) // TODO: make something to handle subtitles

        statusObservable = remoteService!!.vlcService.getVLCStatus(remoteService!!.connection.basicToken())

        scheduler = Schedulers.io()

        subscribeRefresh()

        return view
    }

    private fun subscribeRefresh() {
        subscription = statusObservable
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .retry(10)
                .repeatWhen { t: Observable<Any> -> t.delay(1, TimeUnit.SECONDS) }
                .subscribe(this::handleSuccess, this::handleError)
    }

    private fun unsubscribeRefresh() {
        if (!subscription.isDisposed)
            subscription.dispose()
    }

    override fun onPause() {
        super.onPause()
        unsubscribeRefresh()
    }

    override fun onResume() {
        super.onResume()
        if (subscription.isDisposed)
            subscribeRefresh()
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    private fun handleSuccess(status: Status) {
        view!!.remote_controller!!.injectStatus(status)
        view!!.remote_header!!.injectStatus(status)
    }

    override fun onSeeking(progress: Int) {
        remoteService!!.vlcService!!.seek(remoteService!!.connection!!.basicToken(), "$progress%")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onChangingVolume(progress: Int) {
        remoteService!!.vlcService!!.changeVolume(remoteService!!.connection!!.basicToken(), progress)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickStop() {
        remoteService!!.vlcService!!.stop(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    fun onClickFullScreen() {
        remoteService!!.vlcService!!.toggleFullscreen(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickBack() {
        if (remoteService == null) return
        Toast.makeText(context, "Remote Back", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.jumpPrevious(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickStart() {
        if (remoteService == null) return
        Toast.makeText(context, "Remote Play/Pause", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.togglePlayPause(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickNext() {
        if (remoteService == null) return
        Toast.makeText(context, "Remote Next", Toast.LENGTH_SHORT).show()
        remoteService!!.vlcService!!.jumpNext(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickRandom() {
        remoteService!!.vlcService!!.toggleRandom(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickRepeat() {
        remoteService!!.vlcService!!.toggleRepeat(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun onClickLoop() {
        remoteService!!.vlcService!!.toggleLoop(remoteService!!.connection!!.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }

    override fun refresh() {
        Log.d(TAG, "start refresh...")
    }
}