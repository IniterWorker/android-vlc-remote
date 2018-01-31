package com.epitech.vlcremote.models

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Stats(

	@field:SerializedName("demuxbitrate")
	val demuxbitrate: Double? = null,

	@field:SerializedName("sentbytes")
	val sentbytes: Int? = null,

	@field:SerializedName("demuxcorrupted")
	val demuxcorrupted: Int? = null,

	@field:SerializedName("averagedemuxbitrate")
	val averagedemuxbitrate: Double? = null,

	@field:SerializedName("lostpictures")
	val lostpictures: Int? = null,

	@field:SerializedName("readpackets")
	val readpackets: Int? = null,

	@field:SerializedName("decodedaudio")
	val decodedaudio: Int? = null,

	@field:SerializedName("displayedpictures")
	val displayedpictures: Int? = null,

	@field:SerializedName("playedabuffers")
	val playedabuffers: Int? = null,

	@field:SerializedName("averageinputbitrate")
	val averageinputbitrate: Double? = null,

	@field:SerializedName("lostabuffers")
	val lostabuffers: Int? = null,

	@field:SerializedName("decodedvideo")
	val decodedvideo: Int? = null,

	@field:SerializedName("demuxdiscontinuity")
	val demuxdiscontinuity: Int? = null,

	@field:SerializedName("sentpackets")
	val sentpackets: Int? = null,

	@field:SerializedName("inputbitrate")
	val inputbitrate: Double? = null,

	@field:SerializedName("demuxreadpackets")
	val demuxreadpackets: Int? = null,

	@field:SerializedName("readbytes")
	val readbytes: Int? = null,

	@field:SerializedName("sendbitrate")
	val sendbitrate: Double? = null,

	@field:SerializedName("demuxreadbytes")
	val demuxreadbytes: Int? = null
)