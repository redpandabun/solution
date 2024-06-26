package com.mysolution.common.web.error

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.error.exceptionhandler.ErrorExceptionHandler
import jakarta.servlet.FilterChain
import org.mockito.Mockito.*
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
class WebExceptionHandlerFilterTest {

  @Test
  fun `필터 체인 중 예외가 발생하면 예외를 핸들링해야한다`() {
    // given
    val errorInfo = ErrorInfo.notFound
    val exception = RuntimeException()
    val handler = mock<ErrorExceptionHandler>()
    val filterChain = mock<FilterChain>()
    `when`(handler.handle(exception)).thenReturn(errorInfo)

    val request = MockHttpServletRequest()
    val response = MockHttpServletResponse()
    val filter = WebExceptionHandlerFilter(handler)

    // when
    `when`(filterChain.doFilter(request, response)).thenThrow(exception)

    // then
    filter.doFilter(request, response, filterChain)

    verify(handler).handle(exception)
    assertEquals(errorInfo.code.httpStatus.value(), response.status)
    assertEquals("""{"code":"${errorInfo.code.name}"}""", response.contentAsString)
  }
}
