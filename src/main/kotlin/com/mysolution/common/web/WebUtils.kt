package com.mysolution.common.web

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.serialize.Json
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.HttpMediaTypeNotSupportedException

/**
 * 웹 관련 유틸리티
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
object WebUtils {
  /** 기본 미디어 타입 */
  val DEFAULT_MEDIA_TYPE = APPLICATION_JSON

  /**
   * 지원하는 미디어 타입 목록
   *
   * 이 목록에 미디어 타입을 추가하거나 제거하는 경우 [serializeBody] 메서드도 함께 수정해야 한다
   *
   * 이 목록에 없는 미디어 타입을 사용하려고 할 때는 [HttpMediaTypeNotSupportedException] 예외를 발생시켜야 한다
   */
  val SUPPORT_MEDIA_TYPES = listOf(APPLICATION_JSON)


  /**
   * 컨텐츠 타입에 따라 본문을 문자열로 변환한다
   *
   * @param body 변환할 본문
   * @param contentType 컨텐츠 타입 (기본값: [DEFAULT_MEDIA_TYPE])
   */
  fun serializeBody(body: Any, contentType: MediaType = DEFAULT_MEDIA_TYPE) = when (contentType) {
    APPLICATION_JSON -> Json.stringify(body)
    else -> throw HttpMediaTypeNotSupportedException(
      "Unsupported content type: $contentType",
      SUPPORT_MEDIA_TYPES
    )
  }

  /**
   * HTTP 응답을 작성한다
   *
   * @param body 응답 본문
   * @param status HTTP 상태 코드 (기본값: [OK])
   * @param contentType 컨텐츠 타입 (기본값: [DEFAULT_MEDIA_TYPE])
   */
  fun HttpServletResponse.write(
    body: Any? = null,
    status: HttpStatus = OK,
    contentType: MediaType = DEFAULT_MEDIA_TYPE,
  ) {
    this.status = status.value()

    if (body != null) {
      this.contentType = contentType.toString()

      val bodyText = serializeBody(body, contentType)

      this.writer.use {
        it.write(bodyText)
        it.flush()
      }
    }
  }

  /**
   * HTTP 오류 응답을 작성한다
   *
   * @param error 오류 정보
   * @param contentType 컨텐츠 타입 (기본값: [DEFAULT_MEDIA_TYPE])
   */
  fun HttpServletResponse.writeError(
    error: ErrorInfo,
    contentType: MediaType = DEFAULT_MEDIA_TYPE,
  ) = write(
    body = error,
    status = error.code.httpStatus,
    contentType = contentType
  )
}
