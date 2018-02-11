package com.epitech.vlcremote

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.LayoutInflaterCompat
import android.util.Log
import android.widget.Toast
import com.epitech.vlcremote.fragments.ConnectionEditFragment
import com.epitech.vlcremote.fragments.ConnectionListFragment
import com.epitech.vlcremote.further.replaceFragment
import com.epitech.vlcremote.models.Connection
import com.mikepenz.iconics.context.IconicsLayoutInflater2
import com.vicpin.krealmextensions.count
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity :
        RealmActivity(),
        ConnectionEditFragment.OnActionBtnListener,
        ConnectionListFragment.OnEventListListener {

    private lateinit var editFragment: ConnectionEditFragment
    private lateinit var listFragment: ConnectionListFragment
    private var editMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(layoutInflater, IconicsLayoutInflater2(delegate))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (BuildConfig.DEBUG)
            initDefaultDatabase()

        // declare list fragment
        listFragment = ConnectionListFragment()
        listFragment.setOnEventListListener(this)

        // declare edit fragment
        editFragment = ConnectionEditFragment()
        editFragment.setOnActionBtnListener(this)

        replaceFragment(listFragment, R.id.frag_home_ctn)

        btn_add_connection.setOnClickListener {
            btn_add_connection.hide()

            editMode = true

            replaceFragment(editFragment, R.id.frag_home_ctn)
        }
    }

    private fun initDefaultDatabase() {
        val connection = Connection()

        connection.autoPrimaryKey()
        connection.name = "Nom de ma connection"
        connection.ipaddr = "192.168.1.44"
        connection.port = 8080
        connection.setBasicAuth("", "toto")
        connection.save()

        Log.d("DB", "entry number: " + Connection().count())
    }

    private fun switchOnListFragment() {
        editMode = false

        btn_add_connection.show()

        replaceFragment(listFragment, R.id.frag_home_ctn)
    }

    override fun onEdit(connection: Connection, position: Int) {
        val instanceFragment = ConnectionEditFragment.newInstance(connection.id)

        instanceFragment.setOnActionBtnListener(this)

        btn_add_connection.hide()

        editMode = true

        replaceFragment(instanceFragment, R.id.frag_home_ctn)

        Toast.makeText(this, "onEdit", Toast.LENGTH_SHORT).show()
    }

    override fun onRemove(connection: Connection, position: Int) {
        Toast.makeText(this, "onRemove", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(connection: Connection, position: Int) {
        val intent = Intent(this, RemoteActivity::class.java)
        val bundle = Bundle()

        bundle.putInt("id", connection.id)

        intent.putExtras(bundle)

        startActivity(intent)

        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show()
    }

    override fun onClickEdit(connection: Connection) {
        switchOnListFragment()

        btn_add_connection.show()

        Toast.makeText(this, "onClickEdit", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorId(id: Int) {
        switchOnListFragment()

        Toast.makeText(this, "Error ID", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (editMode) {
            switchOnListFragment()
        } else {
            super.onBackPressed()
        }
    }
}