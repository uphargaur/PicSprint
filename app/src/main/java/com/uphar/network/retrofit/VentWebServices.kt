package com.agrireach.referandreward.datasource.retrofit

import com.uphar.network.CoverageNetwork
import com.uphar.network.ImageResponseNetwork
import retrofit2.Response
import retrofit2.http.GET


interface VentWebServices {


    @GET("https://acharyaprashant.org/api/v2/content/misc/media-coverages?limit=100")
    suspend fun getToken( ): Response<List<CoverageNetwork>>


}