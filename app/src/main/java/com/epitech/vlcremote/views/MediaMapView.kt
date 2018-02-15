package com.epitech.vlcremote.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.epitech.vlcremote.R
import com.epitech.vlcremote.models.Status
import kotlinx.android.synthetic.main.view_media_map.view.*

/**
 * Created by initerworker on 15/02/18.
 */

class MediaMapView @kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
: ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_media_map, this)
    }

    fun injectStatus(status: Status) {
        when {
            status.information!!.category!!.meta!!.title != null ->
                remote_tv_title.text = status.information.category!!.meta!!.title
            status.information!!.category!!.meta!!.filename != null ->
                remote_tv_title.text = status.information.category!!.meta!!.filename
            else ->
                remote_tv_title.text = "Unknown"
        }
        remote_tv_quick.text = ""
        if (status.information.category!!.meta!!.artist != null )
            remote_tv_quick.text = status.information.category.meta!!.artist
    }
}