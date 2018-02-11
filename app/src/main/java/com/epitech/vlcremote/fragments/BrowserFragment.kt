package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epitech.vlcremote.R
import com.epitech.vlcremote.adapters.BrowserRecyclerViewAdapter
import com.epitech.vlcremote.models.Browse
import com.epitech.vlcremote.models.ElementItem
import com.epitech.vlcremote.services.RemoteService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_browser.view.*

/**
* Created by initerworker on 05/02/18.
*/

class BrowserFragment : TabFragment() {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView

    private val TAG: String = "BrowserFragment"
    private var currentPath: String = "file:///"

    var remoteService: RemoteService? = null

    companion object {
        fun newInstance(): BrowserFragment {
            return BrowserFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // swiper
        swipeRefreshLayout = inflater!!.inflate(R.layout.fragment_browser, container, false) as SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }
        // recycler
        recyclerView = swipeRefreshLayout.itemlist_browser_recycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        refresh()
        return swipeRefreshLayout
    }

    private fun handleSuccess(browse: Browse) {
        Log.d(TAG, "Browse count elements: ${browse.element.count()}")
        recyclerView.adapter = BrowserRecyclerViewAdapter(browse, this::handleClickItem)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun handleError(t: Throwable) {
        Log.e(TAG, t.message)
    }

    private fun handleClickItem(item: ElementItem) {
        if (item.type!!.contains("dir")) {
            currentPath = item.uri!!
            refresh()
        }
        else {
            // TODO: make somethings more serious
            remoteService!!.vlcService.playFile(remoteService!!.connection.basicToken(), item.uri!!)
                    .doOnDispose {
                        swipeRefreshLayout.isRefreshing = true
                    }
                    .doOnComplete {
                        swipeRefreshLayout.isRefreshing = false
                    }
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                    }, this::handleError)
        }
    }

    override fun refresh() {
        Log.d(TAG, "Start refresh...")
        remoteService!!.vlcService!!.getVLCBrowse(remoteService!!.connection.basicToken(), currentPath)
                .doOnDispose {
                    swipeRefreshLayout.isRefreshing = true
                }
                .doOnComplete {
                    swipeRefreshLayout.isRefreshing = false
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }
}