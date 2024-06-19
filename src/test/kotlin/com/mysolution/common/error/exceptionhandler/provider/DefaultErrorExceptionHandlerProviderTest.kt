package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
internal class DefaultErrorExceptionHandlerProviderTest {

  @Test
  fun `기본 예외 핸들러 테스트`() {
    val errorInfo = DefaultErrorExceptionHandlerProvider.handle(Exception())

    assertEquals(ErrorInfo.serverError, errorInfo)
  }
}
