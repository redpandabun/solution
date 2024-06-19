package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo
import jakarta.servlet.ServletException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.ErrorResponse

/**
 * [ServletException] 구현체 처리를 위한 [ErrorExceptionHandlerProvider]
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Component
object ServletExceptionErrorHandlerProvider : ErrorExceptionHandlerProvider {
  override fun canHandle(exception: Exception): Boolean = exception is ServletException

  override fun handle(exception: Exception): ErrorInfo {
    if (exception !is ServletException) {
      throw IllegalArgumentException("exception is not ServletException")
    }

    if (exception !is ErrorResponse) {
      return DefaultErrorExceptionHandlerProvider.handle(exception)
    }

    return when (val status = exception.statusCode) {
      HttpStatus.UNAUTHORIZED -> ErrorInfo.requiresAuthentication
      HttpStatus.FORBIDDEN -> ErrorInfo.permissionDenied
      HttpStatus.NOT_FOUND -> ErrorInfo.notFound
      else -> when {
        status.is4xxClientError -> ErrorInfo.invalidRequest
        status.is5xxServerError -> ErrorInfo.serverError
        else -> DefaultErrorExceptionHandlerProvider.handle(exception)
      }
    }
  }
}
