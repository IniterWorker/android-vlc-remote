package com.epitech.vlcremote.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Connection
import com.vicpin.krealmextensions.queryAll
import io.realm.Realm
import kotlinx.android.synthetic.main.itemlist_connection.view.*

/**
 * Created by initerworker on 30/01/18.
 */


class ConnectionRecyclerViewAdapter : AbstractRealmAdapter<Connection, ConnectionRecyclerViewAdapter.ConnectionViewHolder>(Connection().queryAll()) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ConnectionViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemlist_connection, parent, false)
        return ConnectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConnectionViewHolder?, position: Int) {
        holder!!.bind(mResults[position])
    }

    class ConnectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Connection) = with(itemView) {
            itemView.itemlist_addr.text = context.getString(R.string.itemlist_co_addr_format).format(item.ipaddr, item.port)
            itemView.itemlist_name.text = item.name
        }
    }
}