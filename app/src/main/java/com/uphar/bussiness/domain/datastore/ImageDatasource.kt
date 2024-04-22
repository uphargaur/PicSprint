package com.uphar.bussiness.domain.datastore

import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.uphar.bussiness.domain.data.ImageResponse
import kotlinx.coroutines.flow.Flow

interface ImageDatasource {

    suspend fun gettoken(
    ): Flow<DataState<ImageResponse>>
}
