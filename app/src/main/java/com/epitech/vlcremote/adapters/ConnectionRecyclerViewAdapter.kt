package com.epitech.vlcremote.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Connection
import com.vicpin.krealmextensions.queryAll
import io.realm.RealmChangeListener
import kotlinx.android.synthetic.main.itemlist_connection.view.*

/**
* Created by initerworker on 30/01/18.
*/


class ConnectionRecyclerViewAdapter(val listener: (Connection, Int) -> Unit)
    : AbstractRealmAdapter<Connection, ConnectionRecyclerViewAdapter.ConnectionViewHolder>(Connection().queryAll()) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ConnectionViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemlist_connection, parent, false)
        return ConnectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConnectionViewHolder?, position: Int) {
        holder!!.bind(mResults[position], position, listener)
    }

    class ConnectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val leftSide: RelativeLayout = itemView.itemlist_left
        val rightSide: RelativeLayout = itemView.itemlist_right
        val foregroundSide: RelativeLayout = itemView.itemlist_foreground

        fun bind(item: Connection, position: Int, listener: (Connection, Int) -> Unit) = with(itemView) {
            itemView.itemlist_addr.text = context.getString(R.string.itemlist_co_addr_format).format(item.id, item.ipaddr, item.port)
            itemView.itemlist_name.text = item.name
            setOnClickListener {
                listener(item, position)
            }
        }
    }
}