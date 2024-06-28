package com.mysolution.common.error

import org.springframework.http.HttpStatus

/**
 * 오류 코드 정의
 *
 * @property httpStatus 오류가 웹에서 발생하는 경우 매핑되는 기본 HTTP 상태 코드
 * @author RedPandaBun
 * @since 0.1.0
 */
enum class ErrorCode(val httpStatus: HttpStatus) {
  VALIDATION_FAILED(httpStatus = HttpStatus.BAD_REQUEST),
  REQUIRES_BODY(httpStatus = HttpStatus.BAD_REQUEST),
  ALREADY_USERNAME(httpStatus = HttpStatus.CONFLICT),

  // HTTP 상태코드와 1:1로 대응되는 오류 코드
  INVALID_REQUEST(httpStatus = HttpStatus.BAD_REQUEST),
  REQUIRES_AUTHENTICATION(httpStatus = HttpStatus.UNAUTHORIZED),
  PERMISSION_DENIED(httpStatus = HttpStatus.FORBIDDEN),
  NOT_FOUND(httpStatus = HttpStatus.NOT_FOUND),
  SERVER_ERROR(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR)
}
