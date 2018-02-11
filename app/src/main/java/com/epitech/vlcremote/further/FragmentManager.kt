package com.epitech.vlcremote.further

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
* Created by initerworker on 31/01/18.
*/

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}