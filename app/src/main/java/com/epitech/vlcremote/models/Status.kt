package com.epitech.vlcremote.models

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Status(

	@field:SerializedName("length")
	val length: Int? = null,

	@field:SerializedName("apiversion")
	val apiversion: Int? = null,

	@field:SerializedName("version")
	val version: String? = null,

	@field:SerializedName("subtitledelay")
	val subtitledelay: Int? = null,

	@field:SerializedName("audiofilters")
	val audiofilters: Audiofilters? = null,

	@field:SerializedName("volume")
	val volume: Double? = null,

	@field:SerializedName("random")
	val random: Boolean? = null,

	@field:SerializedName("currentplid")
	val currentplid: Int? = null,

	@field:SerializedName("audiodelay")
	val audiodelay: Int? = null,

	@field:SerializedName("fullscreen")
	val fullscreen: Int? = null,

	@field:SerializedName("videoeffects")
	val videoeffects: Videoeffects? = null,

	@field:SerializedName("rate")
	val rate: Int? = null,

	@field:SerializedName("equalizer")
	val equalizer: List<Any?>? = null,

	@field:SerializedName("loop")
	val loop: Boolean? = null,

	@field:SerializedName("repeat")
	val repeat: Boolean? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("position")
	val position: Int? = null,

	@field:SerializedName("time")
	val time: Int? = null
)