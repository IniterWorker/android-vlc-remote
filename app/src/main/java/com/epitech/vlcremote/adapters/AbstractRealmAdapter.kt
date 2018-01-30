package com.epitech.vlcremote.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.realm.RealmObject
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.realm.RealmResults
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.realm.Realm
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.epitech.vlcremote.models.Connection
import io.realm.RealmChangeListener


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


    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    /**
     * @param position the position within our adapter inclusive of headers,items and footers
     * @return an item only if it is not a header or a footer, otherwise returns null
     */
    fun getItem(position: Int): T? {
        return mResults[position]
    }

    override fun getItemCount(): Int = count

    fun setData(results: RealmResults<T>) {
        mResults = results
        notifyDataSetChanged()
    }
}