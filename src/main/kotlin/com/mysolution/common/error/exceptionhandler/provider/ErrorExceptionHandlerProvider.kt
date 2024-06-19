package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo

/**
 * 예외([Exception])을 전달받아 오류 응답([ErrorInfo])을 생성하는 [ErrorExceptionHandlerProvider] 인터페이스
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
interface ErrorExceptionHandlerProvider {

  /**
   * 전달받은 예외 [exception]을 이 핸들러가 처리할 수 있는지 여부를 반환한다
   *
   * @param exception 확인 할 예외
   * @return 이 핸들러가 처리할 수 있는 예외라면 true, 아니라면 false
   */
  fun canHandle(exception: Exception): Boolean

  /**
   * 전달받은 예외 [exception]을 처리하여 오류 응답([ErrorInfo])을 생성한다
   *
   * @param exception 처리할 예외
   * @return 처리된 오류 응답 객체
   */
  fun handle(exception: Exception): ErrorInfo
}
