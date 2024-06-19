package com.mysolution.common.web

import com.mysolution.common.web.WebUtils.write
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_XML
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.HttpMediaTypeNotSupportedException
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
internal class WebUtilsTest {
  @Test
  fun `serializeBody는 미디어 유형이 애플리케이션 json인 경우 json을 반환해야 한다`() {
    val body = mapOf("key" to "value")
    val expected = """{"key":"value"}"""

    val actual = WebUtils.serializeBody(body, APPLICATION_JSON)
    assertEquals(expected, actual)
  }

  @Test
  fun `serializeBody는 미디어 유형이 지원되지 않으면 예외가 발생해야 한다`() {
    val body = mapOf("key" to "value")
    val unsupportedMediaType = APPLICATION_XML

    assertThrows<HttpMediaTypeNotSupportedException> {
      WebUtils.serializeBody(body, unsupportedMediaType)
    }
  }

  @Test
  fun `write는 본문이 null이 아닐 때 본문을 작성해야 한다`() {
    val response = MockHttpServletResponse()
    val body = mapOf("key" to "value")

    response.write(body, HttpStatus.CREATED, APPLICATION_JSON)

    assertEquals(201, response.status)
    assertEquals("application/json", response.contentType)
    assertEquals("""{"key":"value"}""", response.contentAsString)
  }

  @Test
  fun `write는 본문이 null인 경우에만 상태를 설정해야 한다`() {
    val response = MockHttpServletResponse()

    response.write()

    assertEquals(200, response.status)
    assertEquals(null, response.contentType)
    assertEquals("", response.contentAsString)
  }
}
