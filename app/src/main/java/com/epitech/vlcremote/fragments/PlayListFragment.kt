package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epitech.vlcremote.R
import com.epitech.vlcremote.adapters.PlaylistRecyclerViewAdapter
import com.epitech.vlcremote.models.Playlist
import com.epitech.vlcremote.services.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
* Created by initerworker on 05/02/18.
*/

class PlayListFragment : TabFragment() {

    var remoteService: RemoteService? = null
    var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance(): PlayListFragment {
            return PlayListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        recyclerView = inflater!!.inflate(R.layout.fragment_playlist, container, false) as RecyclerView
        refresh()
        return recyclerView as RecyclerView
    }

    private fun handleSuccess(playlist: Playlist) {
        Toast.makeText(context, """Success: ${playlist.children!!.size}""", Toast.LENGTH_SHORT).show()
        recyclerView?.adapter = PlaylistRecyclerViewAdapter(playlist)

    }

    private fun handleError(t: Throwable) {
        Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        Log.d("TabFragment", "refresh")
        /*
        remoteService!!.vlcService!!.getVLCPlaylist(remoteService!!.connection.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
        */
    }
}