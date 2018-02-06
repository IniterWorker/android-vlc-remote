package com.epitech.vlcremote.services

import com.epitech.vlcremote.models.Browse
import com.epitech.vlcremote.models.Playlist
import com.epitech.vlcremote.models.Status
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
* Created by bonett_w on 1/29/18.
*/

interface VLCService {
    @GET("requests/status.json")
    fun getVLCStatus(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/playlist.json")
    fun getVLCPlaylist(
            @Header("Authorization") auth: String
    ) : Observable<Playlist>
    @GET("requests/browse.json?uri=file:///{path}")
    fun getVLCBrowse(
            @Header("Authorization") auth: String,
            @Path("path") path: String
    ) : Observable<Browse>
    @GET("requests/status.json?command=pl_pause")
    fun togglePlayPause(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_next")
    fun jumpNext(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_previous")
    fun jumpPrevious(
            @Header("Authorization") auth: String
    ) : Observable<Status>
}