package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorCode.INVALID_REQUEST
import com.mysolution.common.error.ErrorInfo
import org.springframework.http.converter.HttpMessageNotReadableException

/**
 * [HttpMessageNotReadableException] 처리를 위한 [ErrorExceptionHandlerProvider]
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
object HttpMessageNotReadableExceptionHandlerProvider : ErrorExceptionHandlerProvider {
  override fun canHandle(exception: Exception): Boolean = exception is HttpMessageNotReadableException

  override fun handle(exception: Exception): ErrorInfo {
    if (exception !is HttpMessageNotReadableException) {
      throw IllegalArgumentException("This provider can only handle HttpMessageNotReadableException")
    }

    return ErrorInfo(
      code = INVALID_REQUEST,
      details = mapOf(
        "type" to "EmptyBody"
      )
    )
  }
}
