package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.logging.createLogger
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * 기본 오류 핸들러
 *
 * 알맞은 예외 핸들러를 찾을 수 없을 때 사용되는 핸들러
 *
 * 로그에 예외 발생을 알리고 [ErrorInfo.serverError]를 반환한다.
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Component
@Order(LOWEST_PRECEDENCE)
object DefaultErrorExceptionHandlerProvider : ErrorExceptionHandlerProvider {
  private final val log = createLogger {}

  override fun canHandle(exception: Exception) = true // 항상 처리 가능
  override fun handle(exception: Exception): ErrorInfo {
    log.error(exception) { "Unhandled exception occurred" }

    return ErrorInfo.serverError
  }
}
