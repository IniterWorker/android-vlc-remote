package com.epitech.vlcremote.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.epitech.vlcremote.fragments.TabFragment


/**
* Created by initerworker on 06/02/18.
*/

class RemoteViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragments = ArrayList<Fragment>()

    var currentFragment: TabFragment? = null

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun setPrimaryItem(container: ViewGroup?, position: Int, `object`: Any) {
        if (currentFragment !== `object`) {
            currentFragment = `object` as TabFragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}