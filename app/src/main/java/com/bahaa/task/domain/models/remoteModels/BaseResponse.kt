package com.bahaa.task.domain.models.remoteModels

data class BaseResponse<T>(
    val code: Int?=null,
    val `data`: T,
    val msg: String?=null
)