package com.epitech.vlcremote.models

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class PlaylistItem(

	@field:SerializedName("duration")
	val duration: Int,

	@field:SerializedName("current")
	val current: String? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("ro")
	val ro: String,

	@field:SerializedName("uri")
	val uri: String
)