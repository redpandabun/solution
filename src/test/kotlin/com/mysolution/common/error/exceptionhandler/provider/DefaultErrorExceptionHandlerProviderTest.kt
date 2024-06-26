package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
@Suppress("SpellCheckingInspection")
internal class DefaultErrorExceptionHandlerProviderTest {

  @Test
  fun `기본 예외 핸들러는 canHandle 호출 시 무조건 true를 반환해야 한다`() {
    val exception = Mockito.mock<Exception>()

    assertTrue {
      DefaultErrorExceptionHandlerProvider.canHandle(exception)
    }
  }

  @Test
  fun `기본 예외 핸들러의 handle 호출 시 전달받은 예외와 상관없이 무조건 serverError를 반환해야 한다`() {
    val errorInfo = DefaultErrorExceptionHandlerProvider.handle(Exception())

    assertEquals(ErrorInfo.serverError, errorInfo)
  }
}
