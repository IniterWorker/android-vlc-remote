package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    var remoteService: RemoteService? = null
    var currentPath: String = "/"

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
        Toast.makeText(context, "Browse count = ${browse.element.count()}", Toast.LENGTH_SHORT).show()
        recyclerView.adapter = BrowserRecyclerViewAdapter(browse, this::handleClickItem)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun handleError(t: Throwable) {
        Toast.makeText(context, "Not ok", Toast.LENGTH_SHORT).show()
    }

    private fun handleClickItem(item: ElementItem) {
        currentPath = item.uri
        refresh()
    }

    override fun refresh() {
        remoteService!!.vlcService!!.getVLCBrowse(remoteService!!.connection.basicToken(), "file://$currentPath")
                .doOnDispose { swipeRefreshLayout.isRefreshing = true }
                .doOnComplete {
                    swipeRefreshLayout.isRefreshing = false
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
    }
}