package com.jhalakXCorp.vent.datasource.retrofit




import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.uphar.bussiness.domain.data.ImageResponse
import com.uphar.network.CoverageNetwork
import com.uphar.network.ImageResponseNetwork
import kotlinx.coroutines.flow.Flow

interface VentRetrofitService {


    suspend fun gettoken(
    ): Flow<DataState<List<CoverageNetwork>>>// =>
    //https://uphar.free.beeceptor.com/token





}