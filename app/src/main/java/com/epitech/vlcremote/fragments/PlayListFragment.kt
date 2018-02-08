package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epitech.vlcremote.R
import com.epitech.vlcremote.adapters.PlaylistRecyclerViewAdapter
import com.epitech.vlcremote.models.PlaylistItem
import com.epitech.vlcremote.models.PlaylistRoot
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
        recyclerView?.layoutManager = LinearLayoutManager(context)
        refresh()
        return recyclerView as RecyclerView
    }

    private fun handleSuccess(playlist: PlaylistRoot) {
        recyclerView?.adapter = PlaylistRecyclerViewAdapter(playlist.children!![0]!!, this::handleClickItem)
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun handleError(t: Throwable) {
        Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show()
    }

    private fun handleClickItem(item: PlaylistItem) {
        remoteService?.vlcService!!.playElem(remoteService!!.connection.basicToken(), item.id.toInt())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    refresh()
                }, {

                })
    }

    override fun refresh() {
        remoteService?.vlcService!!.getVLCPlaylist(remoteService!!.connection.basicToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }
}