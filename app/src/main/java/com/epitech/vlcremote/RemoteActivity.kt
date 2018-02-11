package com.epitech.vlcremote

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.epitech.vlcremote.adapters.RemoteViewPagerAdapter
import com.epitech.vlcremote.fragments.BrowserFragment
import com.epitech.vlcremote.fragments.PlayListFragment
import com.epitech.vlcremote.fragments.PlayerFragment
import com.epitech.vlcremote.fragments.TabFragment
import com.epitech.vlcremote.models.Connection
import com.epitech.vlcremote.services.RemoteService
import com.mikepenz.iconics.context.IconicsLayoutInflater2
import com.vicpin.krealmextensions.queryFirst
import kotlinx.android.synthetic.main.activity_remote.*
import java.lang.Exception


/**
* Created by bonett_w on 1/29/18.
*/

class RemoteActivity :
        AppCompatActivity(),
        AHBottomNavigation.OnTabSelectedListener,
        AHBottomNavigation.OnNavigationPositionListener {

    private var remoteService: RemoteService? = null
    private var remoteViewPager: RemoteViewPagerAdapter? = null
    private var currentFragment: TabFragment? = null
    private var connection: Connection? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        LayoutInflaterCompat.setFactory2(layoutInflater,  IconicsLayoutInflater2(delegate))

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_remote)

        remoteViewPager = RemoteViewPagerAdapter(supportFragmentManager)

        viewPager.adapter = remoteViewPager

        initNavigationBar()

        title = "Search"

        if (intent.extras != null) {

            connection = queryFirst<Connection> { equalTo("id", intent.extras.getInt("id")) }
            if (connection != null) {

                try {
                    // TODO: make something more clean and efficient
                    remoteService = RemoteService(connection!!)

                    val playlistFragment = PlayListFragment.newInstance()
                    val playerFragment = PlayerFragment.newInstance()
                    val browserFragment = BrowserFragment.newInstance()

                    playerFragment.remoteService = remoteService
                    playlistFragment.remoteService = remoteService
                    browserFragment.remoteService = remoteService

                    remoteViewPager!!.fragments.add(playlistFragment)
                    remoteViewPager!!.fragments.add(playerFragment)
                    remoteViewPager!!.fragments.add(browserFragment)

                    remoteViewPager!!.notifyDataSetChanged()

                    viewPager.setCurrentItem(1, true)
                    bottom_navigation.setCurrentItem(1, false)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "Invalid configuration", Toast.LENGTH_LONG).show()
                    finish()
                }
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
            currentFragment?.refresh()
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