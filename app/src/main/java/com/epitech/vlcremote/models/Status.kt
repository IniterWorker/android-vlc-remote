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
	val subtitledelay: Double? = null,

	@field:SerializedName("audiofilters")
	val audiofilters: Audiofilters? = null,

	@field:SerializedName("volume")
	val volume: Double? = null,

	@field:SerializedName("random")
	val random: Boolean? = null,

	@field:SerializedName("currentplid")
	val currentplid: Int? = null,

	@field:SerializedName("audiodelay")
	val audiodelay: Double? = null,

	@field:SerializedName("fullscreen")
	val fullscreen: Boolean? = null,

	@field:SerializedName("equalizer")
	val equalizer: List<Any?>? = null,

	@field:SerializedName("videoeffects")
	val videoeffects: Videoeffects? = null,

	@field:SerializedName("stats")
	val stats: Stats? = null,

	@field:SerializedName("rate")
	val rate: Double? = null,

	@field:SerializedName("loop")
	val loop: Boolean? = null,

	@field:SerializedName("repeat")
	val repeat: Boolean? = null,

	@field:SerializedName("information")
	val information: Information? = null,

	@field:SerializedName("position")
	val position: Double? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("state")
	val state: String? = null
) {
    private fun formatSecondsToString(seconds: Int) : String {
        var H: Int = 0
        var M: Int = 0
        var S: Int = 0

        M = seconds / 60
        S = seconds % 60
        H = M / 60
        M %= 60

        return "%2d:%2d:%2d".format(H, M, S)
    }

    fun currentTimeFromated() : String = formatSecondsToString(length!!)

    fun endTimeFormated() : String = formatSecondsToString(length!!)
}