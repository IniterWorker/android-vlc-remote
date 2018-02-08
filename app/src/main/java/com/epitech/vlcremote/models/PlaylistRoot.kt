package com.epitech.vlcremote.models

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

/**
* Created by initerworker on 08/02/18.
*/

@Generated("com.robohorse.robopojogenerator")
data class PlaylistRoot(

        @field:SerializedName("children")
        val children: List<Playlist?>? = null,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("type")
        val type: String,

        @field:SerializedName("ro")
        val ro: String
)