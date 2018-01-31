package com.epitech.vlcremote.models

data class Playlist(
	val children: List<ChildrenItem?>? = null,
	val name: String? = null,
	val id: String? = null,
	val type: String? = null,
	val ro: String? = null
)
