package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.epitech.vlcremote.R
import com.epitech.vlcremote.services.RemoteService

/**
* Created by initerworker on 05/02/18.
*/

class BrowserFragment() : TabFragment() {

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
        val view: ConstraintLayout = inflater!!.inflate(R.layout.fragment_browser, container, false) as ConstraintLayout

        // TODO: write all user interface
        with(view) {

        }

        return view
    }

    override fun refresh() {
        Log.d("TabFragment", "refresh")
    }
}