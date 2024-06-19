package com.mysolution.common.error.exceptionhandler

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.error.exceptionhandler.provider.DefaultErrorExceptionHandlerProvider
import com.mysolution.common.error.exceptionhandler.provider.ErrorExceptionHandlerProvider
import org.springframework.stereotype.Component

/**
 * 예외 핸들러
 *
 * [Exception]을 올바른 [ErrorInfo]로 전환하기 위한 핸들러
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Component
class ErrorExceptionHandler(providers: Set<ErrorExceptionHandlerProvider>) {
  private val reversedProviders = providers.reversed() // Spring Ordered 의 경우 역순으로 정렬되어 있음

  /**
   * [ErrorExceptionHandlerProvider] 중 예외를 처리할 수 있는 핸들러를 찾아 처리한다
   *
   * @param exception 처리할 예외
   * @return 처리된 오류 응답 객체
   */
  fun handle(exception: Exception): ErrorInfo = findProvider(exception).handle(exception)

  /**
   * [ErrorExceptionHandlerProvider] 중 예외를 처리할 수 있는 핸들러를 찾아 반환한다
   *
   * 처리할 수 있는 핸들러가 없다면 [DefaultErrorExceptionHandlerProvider]를 반환한다
   *
   * @param exception 처리할 예외
   * @return 처리할 수 있는 핸들러
   */
  private fun findProvider(exception: Exception) = reversedProviders.firstOrNull { it.canHandle(exception) }
    ?: DefaultErrorExceptionHandlerProvider
}
