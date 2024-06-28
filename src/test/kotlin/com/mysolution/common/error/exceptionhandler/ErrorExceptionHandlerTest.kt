package com.mysolution.common.error.exceptionhandler

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.error.exceptionhandler.provider.ErrorExceptionHandlerProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.Test

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
internal class ErrorExceptionHandlerTest {

  @Test
  fun `올바른 예외 핸들러를 찾아 처리한다`() {
    // given
    val exception = mock<Exception>()
    val errorInfo = mock<ErrorInfo>()

    val falseProvider = mock<ErrorExceptionHandlerProvider>()
    val trueProvider = mock<ErrorExceptionHandlerProvider>()
    val errorExceptionHandler = ErrorExceptionHandler(setOf(falseProvider, trueProvider))

    // when
    `when`(falseProvider.canHandle(exception)).thenReturn(false)
    `when`(trueProvider.canHandle(exception)).thenReturn(true)
    `when`(trueProvider.handle(exception)).thenReturn(errorInfo)

    val result = errorExceptionHandler.handle(exception)

    // then
    assertEquals(errorInfo, result)
  }
}
