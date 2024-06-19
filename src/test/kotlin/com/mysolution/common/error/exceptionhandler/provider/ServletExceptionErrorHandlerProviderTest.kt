package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorInfo
import jakarta.servlet.ServletException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponse
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.servlet.NoHandlerFoundException
import kotlin.test.Test
import kotlin.test.assertTrue
import org.junit.jupiter.params.provider.Arguments.of as argumentsOf

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
internal class ServletExceptionErrorHandlerProviderTest {
  companion object {
    @JvmStatic
    fun provideExceptions() = listOf(
      argumentsOf(HttpMediaTypeNotAcceptableException(""), ErrorInfo.invalidRequest),
      argumentsOf(HttpMediaTypeNotSupportedException(""), ErrorInfo.invalidRequest),
      argumentsOf(NoHandlerFoundException("", "", mock()), ErrorInfo.notFound),
      argumentsOf(makeServletException(HttpStatus.BAD_REQUEST), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.UNAUTHORIZED), ErrorInfo.requiresAuthentication),
      argumentsOf(makeServletException(HttpStatus.FORBIDDEN), ErrorInfo.permissionDenied),
      argumentsOf(makeServletException(HttpStatus.NOT_FOUND), ErrorInfo.notFound),
      argumentsOf(makeServletException(HttpStatus.METHOD_NOT_ALLOWED), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.NOT_ACCEPTABLE), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.REQUEST_TIMEOUT), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.CONFLICT), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.GONE), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.PRECONDITION_FAILED), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.UNSUPPORTED_MEDIA_TYPE), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.EXPECTATION_FAILED), ErrorInfo.invalidRequest),
      argumentsOf(makeServletException(HttpStatus.INTERNAL_SERVER_ERROR), ErrorInfo.serverError),
      argumentsOf(makeServletException(HttpStatus.NOT_IMPLEMENTED), ErrorInfo.serverError),
      argumentsOf(makeServletException(HttpStatus.BAD_GATEWAY), ErrorInfo.serverError),
      argumentsOf(makeServletException(HttpStatus.SERVICE_UNAVAILABLE), ErrorInfo.serverError),
      argumentsOf(makeServletException(HttpStatus.GATEWAY_TIMEOUT), ErrorInfo.serverError),
      argumentsOf(makeServletException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED), ErrorInfo.serverError),
      argumentsOf(makeServletException(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED), ErrorInfo.serverError),
    )

    private fun makeServletException(statusCode: HttpStatus): ServletException {
      val e = mock<ServletException>(withSettings().extraInterfaces(ErrorResponse::class.java))
      `when`((e as ErrorResponse).statusCode).thenReturn(statusCode)

      return e
    }
  }

  @ParameterizedTest
  @MethodSource("provideExceptions")
  fun `올바른 응답을 반환해야 한다`(e: Exception, expected: ErrorInfo) {
    assertTrue { ServletExceptionErrorHandlerProvider.canHandle(e) }
    assertEquals(expected, ServletExceptionErrorHandlerProvider.handle(e))
  }


  @Test
  fun `handle 은 ServletException 예외가 아니면 오류를 발생시켜야 한다`() {
    assertThrows<IllegalArgumentException> {
      ServletExceptionErrorHandlerProvider.handle(mock())
    }
  }

  @Test
  fun `handle 은 ErrorResponse를 상속받지 않은 ServletException 예외를 기본 예외 핸들러에게 넘겨줘야한다`() {
    val e = mock<ServletException>()

    val expected = DefaultErrorExceptionHandlerProvider.handle(e)
    val actual = ServletExceptionErrorHandlerProvider.handle(e)

    assertEquals(expected, actual)
  }
}
