package com.epitech.vlcremote.services

import com.epitech.vlcremote.models.Browse
import com.epitech.vlcremote.models.Playlist
import com.epitech.vlcremote.models.PlaylistRoot
import com.epitech.vlcremote.models.Status
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

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
    ) : Observable<PlaylistRoot>
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
    @GET("requests/status.json?command=fullscreen")
    fun toggleFullscreen(
            @Header("Authorization") auth: String
    ) : Observable<Status>

    //
    // val -> see VLC_API.md
    //
    @GET("requests/status.json?command=volume")
    fun changeVolume(
            @Header("Authorization") auth: String,
            @Query("val") value: Int
    ) : Observable<Status>

    //
    // val -> see VLC_API.md
    //
    @GET("requests/status.json?command=seek")
    fun seek(
            @Header("Authorization") auth: String,
            @Query("val") value: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_repeat")
    fun toggleRepeat(
            @Header("Authorization") auth: String
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_random")
    fun toggleRandom(
            @Header("Authorization") auth: String
    ) : Observable<Status>

    //
    //
    /* not tested from there */
    //
    //
    @GET("requests/status.json?command=pl_stop")
    fun stop(
            @Header("Authorization") auth: String
    ) : Observable<Status>

    //
    // id -> id in playlist of elem to play
    //
    @GET("requests/status.json?command=pl_play")
    fun playElem(
            @Header("Authorization") auth: String,
            @Query("id") id: Int
    ) : Observable<Status>

    //
    // path -> path to the file to play. Don't forget to begin path with "file:///"
    //
    @GET("requests/status.json?command=in_play")
    fun playFile(
            @Header("Authorization") auth: String,
            @Query("input") path: String
    ) : Observable<Status>

    //
    // delay -> delay in sec
    //
    @GET("requests/status.json?command=subdelay")
    fun changeSubdelay(
            @Header("Authorization") auth: String,
            @Query("val") delay: Int
    ) : Observable<Status>

    //
    // delay -> delay in sec
    //
    @GET("requests/status.json?command=audiodelay")
    fun changeAudiodelay(
            @Header("Authorization") auth: String,
            @Query("val") delay: Int
    ) : Observable<Status>
    @GET("requests/status.json?command=rate")
    fun changeSpeed(
            @Header("Authorization") auth: String,
            @Query("val") speed: Int
    ) : Observable<Status>
    @GET("requests/status.json?command=pl_loop")
    fun toggleLoop(
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
    // path -> path to the file. Don't forget to begin path with "file:///"
    //
    @GET("requests/status.json?command=in_enqueue")
    fun addFilePlaylist(
            @Header("Authorization") auth: String,
            @Query("input") path: String
    ) : Observable<Status>

    //
    // path -> path to the file. Don't forget to begin path with "file:///"
    //
    @GET("requests/status.json?command=addsubtitle")
    fun addSubtitle(
            @Header("Authorization") auth: String,
            @Query("val") path: String
    ) : Observable<Status>

    //
    // id -> id in playlist of elem to delete
    //
    @GET("requests/status.json?command=pl_delete")
    fun deleteElem(
            @Header("Authorization") auth: String,
            @Query("id") id: Int
    ) : Observable<Status>

    //
    // id & val -> see VLC_API.md
    //
    @GET("requests/status.json?command=pl_sort")
    fun sortPlaylist(
            @Header("Authorization") auth: String,
            @Query("id") id: Int,
            @Query("val") value: Int
    ) : Observable<Status>

    //
    // val -> ratio. see VLC_API.md for valid values
    //
    @GET("requests/status.json?command=aspectratio")
    fun changeAspectRatio(
            @Header("Authorization") auth: String,
            @Query("val") value: String
    ) : Observable<Status>
}