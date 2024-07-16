package com.bahaa.task.domain

import com.bahaa.task.domain.models.remoteModels.TopStore
import com.bahaa.task.utils.Response
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getTopStores(): Flow<Response<List<TopStore>>>

}
