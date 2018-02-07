package com.epitech.vlcremote

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.app.AppCompatActivity
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.epitech.vlcremote.adapters.RemoteViewPagerAdapter
import com.epitech.vlcremote.fragments.BrowserFragment
import com.epitech.vlcremote.fragments.PlayListFragment
import com.epitech.vlcremote.fragments.PlayerFragment
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.RemoteService
import com.mikepenz.iconics.context.IconicsLayoutInflater2
import com.vicpin.krealmextensions.queryFirst
import kotlinx.android.synthetic.main.activity_remote.*


/**
* Created by bonett_w on 1/29/18.
*/

class RemoteActivity :
        AppCompatActivity(),
        AHBottomNavigation.OnTabSelectedListener,
        AHBottomNavigation.OnNavigationPositionListener {

    private var remoteService: RemoteService? = null
    private var remoteViewPager: RemoteViewPagerAdapter? = null
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        LayoutInflaterCompat.setFactory2(layoutInflater,  IconicsLayoutInflater2(delegate))

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_remote)

        remoteViewPager = RemoteViewPagerAdapter(supportFragmentManager)

        viewPager.adapter = remoteViewPager

        initNavigationBar()

        title = "Search"

        if (intent.extras != null) {

            val connection: Connection? = queryFirst<Connection> { equalTo("id", intent.extras.getInt("id")) }
            if (connection != null) {
                remoteService = RemoteService(connection.ipaddr!!, connection.id)

                val playerFragment = PlayListFragment.newInstance()
                val playlistFragment = PlayerFragment.newInstance()
                val browserFragment = BrowserFragment.newInstance()

                playerFragment.remoteService = remoteService
                playlistFragment.remoteService = remoteService
                browserFragment.remoteService = remoteService

                remoteViewPager!!.fragments.add(playerFragment)
                remoteViewPager!!.fragments.add(playlistFragment)
                remoteViewPager!!.fragments.add(browserFragment)

                remoteViewPager!!.notifyDataSetChanged()

                viewPager.setCurrentItem(1, true)

            }
        }
    }

    private fun initNavigationBar() {
        val item1 = AHBottomNavigationItem(R.string.tab_playlist, R.drawable.ic_playlist_play, R.color.colorInactive)
        val item2 = AHBottomNavigationItem(R.string.tab_player, R.drawable.ic_desktop, R.color.colorInactive)
        val item3 = AHBottomNavigationItem(R.string.tab_browser, R.drawable.ic_folder, R.color.colorInactive)

        bottom_navigation.addItem(item1)
        bottom_navigation.addItem(item2)
        bottom_navigation.addItem(item3)

        bottom_navigation.accentColor = ContextCompat.getColor(this, R.color.colorAccent)
        bottom_navigation.inactiveColor = ContextCompat.getColor(this, R.color.colorInactive)
        bottom_navigation.defaultBackgroundColor = ContextCompat.getColor(this, R.color.colorGrey)
        bottom_navigation.isForceTint = true

        // Manage titles
        bottom_navigation.titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE
        bottom_navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottom_navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        bottom_navigation.setOnTabSelectedListener(this)

    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {

        if (currentFragment == null) {
            currentFragment = remoteViewPager!!.currentFragment
        }

        if (wasSelected) {
            // refresh
            return true
        }

        if (currentFragment != null) {
            // will be hidden
        }

        viewPager.setCurrentItem(position, false)

        if (currentFragment == null) {
            return true
        }

        return true
    }

    override fun onPositionChange(y: Int) {
    }
}