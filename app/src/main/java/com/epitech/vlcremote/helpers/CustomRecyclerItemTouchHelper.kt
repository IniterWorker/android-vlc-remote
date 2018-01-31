package com.epitech.vlcremote.helpers

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.widget.Toast
import com.epitech.vlcremote.adapters.ConnectionRecyclerViewAdapter


/**
* Created by initerworker on 30/01/18.
*/


class CustomRecyclerItemTouchHelper(dragDirs: Int, swipeDirs: Int, listener: CustomRecyclerItemTouchHelperListener)
    : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    private val mListener: CustomRecyclerItemTouchHelperListener = listener

    override fun onMove(recyclerView: RecyclerView?,
                        viewHolder: RecyclerView.ViewHolder?,
                        target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder) {
        val view: ConnectionRecyclerViewAdapter.ConnectionViewHolder =
                (viewHolder as ConnectionRecyclerViewAdapter.ConnectionViewHolder)
        val left = view.leftSide
        val right = view.rightSide
        val foreground = view.foregroundSide

        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(left)
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(right)
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(foreground)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        val position: Int = viewHolder!!.adapterPosition

        when {
            (direction and ItemTouchHelper.LEFT) == ItemTouchHelper.LEFT -> mListener.onLeft(viewHolder, direction, position)
            (direction and ItemTouchHelper.RIGHT) == ItemTouchHelper.RIGHT -> mListener.onRight(viewHolder, direction, position)
            (direction and ItemTouchHelper.START) == ItemTouchHelper.START -> mListener.onLeft(viewHolder, direction, position)
            (direction and ItemTouchHelper.END) == ItemTouchHelper.END -> mListener.onRight(viewHolder, direction, position)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                             actionState: Int, isCurrentlyActive: Boolean) {
        val view: ConnectionRecyclerViewAdapter.ConnectionViewHolder =
                (viewHolder as ConnectionRecyclerViewAdapter.ConnectionViewHolder)
        val left = view.leftSide
        val right = view.rightSide
        val foreground = view.foregroundSide

        if (dX > 0) {
            ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, left, dX, dY,
                    actionState, isCurrentlyActive)
        } else {
            ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, right, dX, dY,
                    actionState, isCurrentlyActive)
        }

        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, foreground, dX, dY,
                actionState, isCurrentlyActive)
    }


}