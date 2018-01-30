package com.epitech.vlcremote.models

import android.util.Base64
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Created by bonett_w on 1/29/18.
 */

@RealmClass
open class Connection : RealmObject() {
    @PrimaryKey
    @Expose
    open var id: Int = 0

    @Expose
    open var ipaddr: String? = null

    @Expose
    open var name: String? = null

    @Expose
    open var basicAuth: String? = null

    @Expose
    open var port: Int = 0

    fun setBasicAuth(username: String, password: String) {
        val basic: String = username + ":" + password
        basicAuth = Base64.encodeToString(basic.toByteArray(), Base64.NO_WRAP)
    }
}