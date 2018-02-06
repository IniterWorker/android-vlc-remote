package com.epitech.vlcremote.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup


/**
* Created by initerworker on 06/02/18.
*/

class RemoteViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragments = ArrayList<Fragment>()

    var currentFragment: Fragment? = null

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun setPrimaryItem(container: ViewGroup?, position: Int, `object`: Any) {
        if (currentFragment !== `object`) {
            currentFragment = `object` as Fragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}