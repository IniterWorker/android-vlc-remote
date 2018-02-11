package com.epitech.vlcremote.adapters

import android.support.v7.widget.RecyclerView
import io.realm.RealmObject


/**
* Created by initerworker on 30/01/18.
*/

abstract class AbstractRealmAdapter<T : RealmObject, VH : RecyclerView.ViewHolder>(items: List<T>) : RecyclerView.Adapter<VH>() {

    protected var mResults: List<T> = items

    init {
        notifyDataSetChanged()
    }

    val count: Int
        get() = mResults.size

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getItemViewType(position: Int): Int = 0

    override fun getItemCount(): Int = count

    fun getItem(position: Int): T? = mResults[position]

    fun setData(results: List<T>) {
        mResults = results
        notifyDataSetChanged()
    }
}