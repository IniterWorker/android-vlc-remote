package com.epitech.vlcremote.models

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Videoeffects(

	@field:SerializedName("saturation")
	val saturation: Double? = null,

	@field:SerializedName("brightness")
	val brightness: Double? = null,

	@field:SerializedName("contrast")
	val contrast: Double? = null,

	@field:SerializedName("hue")
	val hue: Double? = null,

	@field:SerializedName("gamma")
	val gamma: Double? = null
)