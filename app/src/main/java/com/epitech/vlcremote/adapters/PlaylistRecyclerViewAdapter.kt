package com.epitech.vlcremote.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Playlist
import com.epitech.vlcremote.models.PlaylistItem
import kotlinx.android.synthetic.main.itemlist_connection.view.*

/**
* Created by initerworker on 07/02/18.
*/

class PlaylistRecyclerViewAdapter(val playlist: Playlist, val listener: (PlaylistItem) -> Unit) : RecyclerView.Adapter<PlaylistRecyclerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemlist_playlist, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = playlist.children!!.count()

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bindValue(playlist.children!![position]!!, listener)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindValue(item: PlaylistItem, listener: (PlaylistItem) -> Unit) = with(itemView) {
            itemlist_name.text = item.name
            if (item.current != null) {
                itemlist_foreground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
            }
            else {
                itemlist_foreground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGrey))
            }
            setOnClickListener {
                listener(item)
            }
        }
    }
}