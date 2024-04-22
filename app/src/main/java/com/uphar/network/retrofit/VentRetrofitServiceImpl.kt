package com.jhalakXCorp.vent.datasource.retrofit

import android.util.Log
import com.agrireach.referandreward.datasource.retrofit.VentWebServices
import com.jhalakXCorp.vent.bussinesss.domain.Utils.getErrorMessage
import com.jhalakXCorp.vent.bussinesss.domain.Utils.getErrorMessageWithOutNetworkError
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.ErrorUtils
import com.uphar.bussiness.domain.data.ImageResponse
import com.uphar.network.CoverageNetwork
import com.uphar.network.ImageResponseNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.CancellationException

class VentRetrofitServiceImpl(
    private val ventRetrofitService: VentWebServices,
    private val errorUtils: ErrorUtils
) : VentRetrofitService {
    override suspend fun gettoken(
    ): Flow<DataState<List<CoverageNetwork>>> = flow {
        emit(DataState.Loading(true))
        try {
            val response = ventRetrofitService.getToken()
            Log.e("token",response.body().toString())
            if (response.isSuccessful) {
                Log.e("Token",response.body().toString())
                emit(DataState.Success(response.body()!!))
                emit(DataState.Loading(false))
            } else {
                val apiError = errorUtils.parseError(response)!!
                if (apiError.message.isNotEmpty()) {
                    emit(DataState.Error(CancellationException(apiError.message[0])))
                } else {
                    emit(DataState.Error(CancellationException("Something went wrong")))
                }
                emit(DataState.Loading(false))
            }
        } catch (e: Exception) {
            Log.e("Tokenerror",e.toString())
            if (getErrorMessage(e).isNotEmpty())
                emit(DataState.Error(CancellationException(getErrorMessageWithOutNetworkError(e))))
            emit(DataState.Loading(false))
        }
    }
}