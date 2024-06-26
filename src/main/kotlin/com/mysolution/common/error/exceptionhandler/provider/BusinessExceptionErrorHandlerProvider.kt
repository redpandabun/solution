package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.exception.BusinessException

/**
 * [BusinessException] 예외를 처리하는 [ErrorExceptionHandlerProvider] 구현체
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
object BusinessExceptionErrorHandlerProvider : ErrorExceptionHandlerProvider {
  override fun canHandle(exception: Exception): Boolean = exception is BusinessException

  override fun handle(exception: Exception): ErrorInfo {
    if (exception !is BusinessException) {
      throw IllegalArgumentException("This provider can only handle BusinessException")
    }

    return exception.error
  }
}
