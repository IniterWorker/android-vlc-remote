package com.epitech.vlcremote.further

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
* Created by initerworker on 31/01/18.
*/


fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}