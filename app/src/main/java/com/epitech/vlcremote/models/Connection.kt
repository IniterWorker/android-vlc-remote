package com.epitech.vlcremote.models

import android.util.Base64
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryFirst
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
* Created by bonett_w on 1/29/18.
*/

open class Connection (
    @PrimaryKey
    @Expose
    var id: Int = 0,

    @Expose
    var ipaddr: String? = null,

    @Expose
    var name: String? = null,

    @Expose
    var basicAuth: String? = null,

    @Expose
    var port: Int = 0

) : RealmObject() {

    fun autoPrimaryKey() {
        val item = queryFirst<Connection> { max("id") }
        id = if (item != null) item.id + 1 else 1
    }

    fun setBasicAuth(username: String, password: String) {
        basicAuth = Base64.encodeToString("$username:$password".toByteArray(), Base64.NO_WRAP)
    }

    fun basicToken() : String {
        return "Basic $basicAuth"
    }
}