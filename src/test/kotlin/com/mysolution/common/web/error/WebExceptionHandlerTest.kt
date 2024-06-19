package com.mysolution.common.web.error

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.error.exceptionhandler.ErrorExceptionHandler
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
internal class WebExceptionHandlerTest {

  @Test
  fun `resolveException 은 올바른 오류 응답을 리턴해야 한다`() {
    // given
    val exception = mock<Exception>()
    val errorInfo = ErrorInfo.serverError

    val handler = mock<ErrorExceptionHandler>()
    val resolver = WebExceptionHandler(handler)
    val request = MockHttpServletRequest()
    val response = MockHttpServletResponse()

    // when
    `when`(handler.handle(exception)).thenReturn(errorInfo)

    resolver.resolveException(request, response, null, exception)

    // then
    assertEquals(500, response.status)
    assertEquals(APPLICATION_JSON_VALUE, response.contentType)
    assertEquals("""{"code":"SERVER_ERROR"}""", response.contentAsString)
  }
}
