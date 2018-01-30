package com.epitech.vlcremote.services

import com.epitech.vlcremote.models.Status
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

/**
* Created by bonett_w on 1/29/18.
*/

interface VLCService {
    @GET("requests/status.json")
    fun getVLCStatus(
            @Header("Authorization") auth: String
    ) : Observable<Status>
}