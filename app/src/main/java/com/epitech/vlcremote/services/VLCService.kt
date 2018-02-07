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

    /* get info from vlc */
    @GET("requests/status.json")
    fun getVLCStatus(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/playlist.json")
    /* not tested from there */
    fun getVLCPlaylist(
            @Header("Authorization") auth: String
    ) : Observable<Playlist>
    @GET("requests/browse.json?uri=file:///{path}")
    fun getVLCBrowse(
            @Header("Authorization") auth: String,
            @Path("path") path: String
    ) : Observable<Browse>

    /* do remote command */
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
    /* not tested from there */
    @GET("requests/status.json?command=pl_stop")
    fun stop(
            @Header("Authorization") auth: String
    ) : Observable<Status>

    //
    // id -> id in playlist of elem to play
    //
    @GET("requests/status.json?command=pl_play&id={id}")
    fun playElem(
            @Header("Authorization") auth: String,
            @Path("id") id: Int
    ) : Observable<Status>

    //
    // path -> path to the file to play
    //
    @GET("requests/status.json?command=in_play&input=file:///{path}")
    fun playFile(
            @Header("Authorization") auth: String,
            @Path("path") path: String
    ) : Observable<Status>
    @GET("requests/status.json?command=fullscreen")
    fun toggleFullscreen(
            @Header("Authorization") auth: String
    ) : Observable<Status>

    //
    // val -> see VLC_API.md
    //
    @GET("requests/status.json?command=seek&val={value}")
    fun seek(
            @Header("Authorization") auth: String,
            @Path("value") value: String
    ) : Observable<Status>

    //
    // val -> see VLC_API.md
    //
    @GET("requests/status.json?command=volume&val={value}")
    fun changeVolume(
            @Header("Authorization") auth: String,
            @Path("value") value: String
    ) : Observable<Status>

    //
    // delay -> delay in sec
    //
    @GET("requests/status.json?command=subdelay&val={delay}")
    fun changeSubdelay(
            @Header("Authorization") auth: String,
            @Path("delay") delay: Int
    ) : Observable<Status>

    //
    // delay -> delay in sec
    //
    @GET("requests/status.json?command=audiodelay&val={delay}")
    fun changeAudiodelay(
            @Header("Authorization") auth: String,
            @Path("delay") delay: Int
    ) : Observable<Status>
    @GET("requests/status.json?command=rate&val={speed}")
    fun changeSpeed(
            @Header("Authorization") auth: String,
            @Path("speed") speed: Int
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_loop")
    fun toggleLoop(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_repeat")
    fun toggleRepeat(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_random")
    fun toggleRandom(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_empty")
    fun emptyPlaylist(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_forceresume")
    fun forcePlay(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_forcepause")
    fun forcePause(
            @Header("Authorization") auth: String
    ) : Observable<Status>

    //
    // path -> path to the file
    //
    @GET("requests/status.json?command=in_enqueue&input=file:///{path}")
    fun addFilePlaylist(
            @Header("Authorization") auth: String,
            @Path("path") path: String
    ) : Observable<Status>

    //
    // path -> path to the file
    //
    @GET("requests/status.json?command=addsubtitle&val=file:///{path}")
    fun addSubtitle(
            @Header("Authorization") auth: String,
            @Path("path") path: String
    ) : Observable<Status>

    //
    // id -> id in playlist of elem to delete
    //
    @GET("requests/status.json?command=pl_delete&id={id}")
    fun deleteElem(
            @Header("Authorization") auth: String,
            @Path("id") id: Int
    ) : Observable<Status>

    //
    // id & val -> see VLC_API.md
    //
    @GET("requests/status.json?command=pl_sort&id={id}&val={value}")
    fun sortPlaylist(
            @Header("Authorization") auth: String,
            @Path("id") id: Int,
            @Path("value") value: Int
    ) : Observable<Status>

    //
    // val -> ratio. see VLC_API.md for valid values
    //
    @GET("requests/status.json?command=aspectratio&val={value}")
    fun changeAspectRatio(
            @Header("Authorization") auth: String,
            @Path("value") value: String
    ) : Observable<Status>
}