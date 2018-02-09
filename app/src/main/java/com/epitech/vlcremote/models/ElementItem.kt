package com.epitech.vlcremote.models

data class ElementItem(
	val creationTime: Int,
	val mode: Int,
	val uid: Int,
	val path: String,
	val gid: Int,
	val size: Int,
	val name: String,
	val type: String,
	val uri: String,
	val modificationTime: Int,
	val accessTime: Int
)
