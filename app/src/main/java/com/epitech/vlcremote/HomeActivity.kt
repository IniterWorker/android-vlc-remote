package com.epitech.vlcremote

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.epitech.vlcremote.fragments.ConnectionEditFragment
import com.epitech.vlcremote.fragments.ConnectionListFragment
import com.epitech.vlcremote.models.Connection
import com.vicpin.krealmextensions.count
import com.vicpin.krealmextensions.save
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), ConnectionEditFragment.OnActionBtnListener, ConnectionListFragment.OnEventListListener {
    private lateinit var editFragment: ConnectionEditFragment
    private lateinit var listFragment: ConnectionListFragment

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

        // declare list fragment
        listFragment = ConnectionListFragment()
        listFragment.setOnEventListListener(this)

        // declare edit fragment
        editFragment = ConnectionEditFragment()
        editFragment.setOnActionBtnListener(this)

        replaceFragment(listFragment, R.id.frag_home_ctn)

        btn_add_connection.setOnClickListener {
            btn_add_connection.hide()
            replaceFragment(editFragment, R.id.frag_home_ctn)
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }

    override fun onEdit(connection: Connection, position: Int) {
        val instanceFragment = ConnectionEditFragment.newInstance(connection.id)

        instanceFragment.setOnActionBtnListener(this)

        // remove btn add
        btn_add_connection.hide()

        replaceFragment(instanceFragment, R.id.frag_home_ctn)

        Toast.makeText(this, "onEdit", Toast.LENGTH_SHORT).show()
    }

    override fun onRemove(connection: Connection, position: Int) {
        Toast.makeText(this, "onRemove", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(connection: Connection, position: Int) {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, RemoteActivity::class.java)
        val bundle = Bundle()

        bundle.putInt("id", connection.id)

        intent.putExtras(bundle)

        startActivity(intent)
    }

    override fun onClickEdit(connection: Connection) {
        Toast.makeText(this, "onClickEdit", Toast.LENGTH_SHORT).show()
        replaceFragment(listFragment, R.id.frag_home_ctn)
        btn_add_connection.show()
    }

    override fun onErrorId(id: Int) {
        replaceFragment(listFragment, R.id.frag_home_ctn)
        Toast.makeText(this, "Error ID", Toast.LENGTH_SHORT).show()
    }
}