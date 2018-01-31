package com.epitech.vlcremote.models

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Stream0(

	@field:SerializedName("Type")
	val type: String? = null,

	@field:SerializedName("Codec")
	val codec: String? = null,

	@field:SerializedName("Bitrate")
	val bitrate: String? = null,

	@field:SerializedName("Channels")
	val channels: String? = null,

	@field:SerializedName("Sample_rate")
	val sampleRate: String? = null
)