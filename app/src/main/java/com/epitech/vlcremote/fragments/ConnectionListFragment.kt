package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epitech.vlcremote.R
import com.epitech.vlcremote.adapters.ConnectionRecyclerViewAdapter
import com.epitech.vlcremote.helpers.CustomRecyclerItemTouchHelper
import com.epitech.vlcremote.helpers.CustomRecyclerItemTouchHelperListener
import com.epitech.vlcremote.models.Connection
import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.equalToValue
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryAll

/**
 * Created by initerworker on 30/01/18.
 */

class ConnectionListFragment : Fragment(), CustomRecyclerItemTouchHelperListener {

    private lateinit var adapter: ConnectionRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private var mListener: OnEventListListener? = null


    /**
     * Delete swipe
     */
    override fun onLeft(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        val connection: Connection = adapter.getItem(position)!!
        if (mListener != null)
            mListener!!.onRemove(connection, position)

        // remove in database
        delete<Connection> {
            equalToValue("id", connection.id)
        }

        // TODO: change method : is not efficient
        adapter.setData(Connection().queryAll())

        adapter.notifyItemRemoved(position)
    }

    /**
     * Edit swipe
     */
    override fun onRight(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        if (mListener != null)
            mListener!!.onEdit(adapter.getItem(position)!!, position)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: RecyclerView = inflater!!.inflate(R.layout.fragment_list_connection, container, false) as RecyclerView

        adapter = ConnectionRecyclerViewAdapter({
            co, pos -> if (mListener != null)
                mListener!!.onClick(co, pos)
        })

        recyclerView = view

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        val itemTouchHelperCallback = CustomRecyclerItemTouchHelper(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

        return view
    }

    /**
     * Set's the OnEventListListener
     */
    fun setOnEventListListener(listener: OnEventListListener) {
        mListener = listener
    }

    /**
     * Simple Listener
     */
    interface OnEventListListener {
        fun onEdit(connection: Connection, position: Int)
        fun onRemove(connection: Connection, position: Int)
        fun onClick(connection: Connection, position: Int)
    }
}