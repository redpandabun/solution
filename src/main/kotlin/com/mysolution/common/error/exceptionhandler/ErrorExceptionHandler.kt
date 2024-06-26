package com.mysolution.common.error.exceptionhandler

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.error.exceptionhandler.provider.DefaultErrorExceptionHandlerProvider
import com.mysolution.common.error.exceptionhandler.provider.ErrorExceptionHandlerProvider
import com.mysolution.common.logging.createLogger
import jakarta.annotation.PostConstruct
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
class ErrorExceptionHandler(private val providers: Set<ErrorExceptionHandlerProvider>) {
  private final val log = createLogger { }

  @PostConstruct
  protected fun init() {
    log.debug { "Mapping providers: " + providers.map { it::class.qualifiedName } }
  }

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
  private fun findProvider(exception: Exception) = providers.first { it.canHandle(exception) }
}
