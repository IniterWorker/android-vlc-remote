package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Connection
import com.vicpin.krealmextensions.createOrUpdate
import com.vicpin.krealmextensions.queryFirst
import kotlinx.android.synthetic.main.fragment_edit_connection.view.*

/**
* Created by initerworker on 30/01/18.
*/

class ConnectionEditFragment : Fragment() {

    private var mId: Int = 0
    private var mConnection: Connection? = null

    private var mListener: OnActionBtnListener? = null

    companion object {
        fun newInstance(id: Int): ConnectionEditFragment {
            val instance = ConnectionEditFragment()
            val bundle = Bundle()

            bundle.putInt("id", id)

            instance.arguments = bundle

            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mConnection = null

        val bundle: Bundle? = arguments
        if (bundle != null) {
            mId = bundle.getInt("id")

            Log.e("BundleID::", mId.toString())

            if (mId >= 0) {

                // query
                mConnection = Connection().queryFirst { equalTo("id", mId) }

                // check or error report
                if (mConnection == null && mListener != null)
                    mListener!!.onErrorId(mId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: ScrollView = inflater!!.inflate(R.layout.fragment_edit_connection, container, false) as ScrollView
        val holder = FormHolder(view)

        if (mConnection == null)
            mConnection = Connection()

        holder.bind(mConnection!!, {

            if (it.id == 0)
                it.autoPrimaryKey()

            holder.bindFromView(it)
            it.createOrUpdate()

            if (mListener != null)
                mListener!!.onClickEdit(it)
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            // restore fragment state here
        }
    }

    fun setOnActionBtnListener(listener: OnActionBtnListener) {
        mListener = listener
    }

    interface OnActionBtnListener {
        fun onClickEdit(connection: Connection)
        fun onErrorId(id: Int)
    }

    class FormHolder(private var itemView: View) {

        fun bind(connection: Connection, listener: (Connection) -> Unit) = with(itemView) {
            input_name.setText(connection.name)
            input_ip.setText(connection.ipaddr)
            input_port.setText(connection.port.toString())
            input_username.setText("")
            input_password.setText("")
            btn_edit.setOnClickListener {
                listener(connection)
            }
        }

        fun bindFromView(connection: Connection) = with(itemView) {
            connection.name = input_name.text.toString()
            connection.ipaddr = input_ip.text.toString()
            connection.setBasicAuth(input_username.text.toString(), input_password.text.toString())
            connection.port = Integer.parseInt(input_port.text.toString())
        }
    }
}