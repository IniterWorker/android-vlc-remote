package com.epitech.vlcremote.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Browse
import com.epitech.vlcremote.models.ElementItem
import kotlinx.android.synthetic.main.itemlist_file.view.*

/**
* Created by initerworker on 09/02/18.
*/

class BrowserRecyclerViewAdapter(val browse: Browse, val listener: (ElementItem) -> Unit) : RecyclerView.Adapter<BrowserRecyclerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemlist_file, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = browse.element.count()

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bindValue(browse.element[position], listener)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindValue(item: ElementItem, listener: (ElementItem) -> Unit) = with(itemView) {
            itemlist_name.text = item.name
            setOnClickListener {
                listener(item)
            }
        }
    }
}