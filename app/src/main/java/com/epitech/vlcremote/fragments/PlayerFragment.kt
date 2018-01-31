package com.epitech.vlcremote.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.epitech.vlcremote.R
import kotlinx.android.synthetic.main.fragment_player.view.*

/**
* Created by initerworker on 31/01/18.
*/

class PlayerFragment() : Fragment() {

    companion object {
        fun newInstance() : PlayerFragment {
            return PlayerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
       val view: LinearLayout = inflater!!.inflate(R.layout.fragment_player, container, false) as LinearLayout

        // TODO: write all user interface
        with(view) {
            remote_back.setOnClickListener {
                Toast.makeText(context, "Remote Back", Toast.LENGTH_SHORT).show()
            }

            remote_start.setOnClickListener {
                Toast.makeText(context, "Remote Play/Pause", Toast.LENGTH_SHORT).show()
            }

            remote_next.setOnClickListener {
                Toast.makeText(context, "Remote Next", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}