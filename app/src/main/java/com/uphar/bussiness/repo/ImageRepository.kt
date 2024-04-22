package com.uphar.bussiness.repo

import com.jhalakXCorp.vent.bussinesss.domain.Utils.exhaustive
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.uphar.bussiness.domain.data.ImageResponse
import com.uphar.bussiness.domain.datastore.ImageDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageRepository constructor(
    private val imageDatasource: ImageDatasource
) {

    suspend fun gettoken(
    ): Flow<DataState<ImageResponse>> = flow {
        imageDatasource.gettoken(
        ).collect {
            when (it) {
                is DataState.Error -> {
                    emit(DataState.Error(it.exception))
                }

                is DataState.Loading -> {
                    emit(DataState.Loading(it.loading))
                }

                is DataState.Success -> {
                    emit(DataState.Success(it.data))
                }
            }.exhaustive
        }
    }
}