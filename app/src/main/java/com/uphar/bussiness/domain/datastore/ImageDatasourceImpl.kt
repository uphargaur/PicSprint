package com.uphar.bussiness.domain.datastore

import android.util.Log
import com.jhalakXCorp.vent.bussinesss.domain.Utils.exhaustive
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.jhalakXCorp.vent.datasource.retrofit.VentRetrofitService
import com.uphar.bussiness.domain.ImageMapper
import com.uphar.bussiness.domain.data.ImageResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageDatasourceImpl constructor(
    private val ventRetrofitService: VentRetrofitService,
    private val imageMapper: ImageMapper,

) : ImageDatasource {

    override suspend fun gettoken(): Flow<DataState<ImageResponse>> = flow {
        ventRetrofitService.gettoken(
        ).collect {
            when (it) {
                is DataState.Error -> {
                    emit(DataState.Error(it.exception))
                }

                is DataState.Loading -> {
                    emit(DataState.Loading(it.loading))
                }

                is DataState.Success -> {
                    Log.d("UploadAd::", "Api Successful")
                    emit(DataState.Success(imageMapper.mapToEntity(it.data)))
                }

            }.exhaustive
        }
    }

}
