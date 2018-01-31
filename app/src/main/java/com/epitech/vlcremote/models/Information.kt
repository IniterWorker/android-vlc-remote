package com.epitech.vlcremote.models

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Information(

	@field:SerializedName("chapter")
	val chapter: Int? = null,

	@field:SerializedName("chapters")
	val chapters: List<Any?>? = null,

	@field:SerializedName("titles")
	val titles: List<Any?>? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("title")
	val title: Int? = null
)