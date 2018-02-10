@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration

/**
* Created by initerworker on 31/01/18.
*/
abstract class RealmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init Realm.io
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
                .name("user-db")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()

        // clear previous data for fresh start
        if (BuildConfig.DEBUG) {
            Realm.deleteRealm(Realm.getDefaultConfiguration())
            Realm.deleteRealm(realmConfiguration)
        } else {
            Realm.setDefaultConfiguration(realmConfiguration)
        }
    }
}