package br.pucpr.HighFlyAPI.exceptions

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(BAD_REQUEST)
class BadRequestException(
    msg: String = BAD_REQUEST.reasonPhrase,
    cause: Throwable? = null
): IllegalStateException(msg, cause)