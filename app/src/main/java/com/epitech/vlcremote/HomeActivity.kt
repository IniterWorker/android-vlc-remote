package com.epitech.vlcremote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.epitech.vlcremote.adapters.ConnectionRecyclerViewAdapter
import com.epitech.vlcremote.models.Connection
import com.vicpin.krealmextensions.count
import com.vicpin.krealmextensions.save
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: ConnectionRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder().name("user-db").schemaVersion(1).deleteRealmIfMigrationNeeded().build()

        // clear previous data for fresh start
        Realm.deleteRealm(Realm.getDefaultConfiguration())
        Realm.deleteRealm(realmConfiguration)


        val connection = Connection()
        connection.name = "Nom de ma connection"
        connection.ipaddr = "192.168.1.44"
        connection.port = 8080
        connection.setBasicAuth("", "toto")
        connection.save()

        Log.d("DB", "entry number: " + Connection().count())

        recyclerView = itemlist_recycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ConnectionRecyclerViewAdapter()
        recyclerView.adapter = adapter
    }
}
