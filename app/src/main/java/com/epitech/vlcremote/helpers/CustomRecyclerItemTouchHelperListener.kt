package com.epitech.vlcremote.helpers

import android.support.v7.widget.RecyclerView

/**
 * Created by initerworker on 30/01/18.
 */

interface CustomRecyclerItemTouchHelperListener {
    fun onLeft(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int)
    fun onRight(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int)
}