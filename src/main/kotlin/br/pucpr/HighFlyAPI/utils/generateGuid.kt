package br.pucpr.HighFlyAPI.utils

import java.util.UUID

fun generateGuid(limiter: Boolean): String {
    val uuid = UUID.randomUUID()
    return if (limiter) uuid.toString().split("-")[0] else uuid.toString()
}