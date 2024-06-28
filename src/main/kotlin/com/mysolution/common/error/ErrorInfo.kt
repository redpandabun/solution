package com.mysolution.common.error

import com.mysolution.common.error.ErrorCode.INVALID_REQUEST

/**
 * 오류 응답 DTO
 *
 * @property code 오류 코드
 * @author RedPandaBun
 * @since 0.1.0
 */
data class ErrorInfo(
  val code: ErrorCode,
  val errors: List<Map<String, Any?>>? = null
) {
  constructor(code: ErrorCode, errors: Map<String, Any?>) : this(code, listOf(errors))
  constructor(code: ErrorCode, errors: Pair<String, Any?>) : this(code, mapOf(errors))

  override fun toString(): String = "${code.httpStatus.value()} ${code.name}"

  companion object {
    // 자주 사용하는 응답
    val invalidRequest by lazy { ErrorInfo(code = INVALID_REQUEST) }
    val requiresAuthentication by lazy { ErrorInfo(code = ErrorCode.REQUIRES_AUTHENTICATION) }
    val permissionDenied by lazy { ErrorInfo(code = ErrorCode.PERMISSION_DENIED) }
    val notFound by lazy { ErrorInfo(code = ErrorCode.NOT_FOUND) }
    val serverError by lazy { ErrorInfo(code = ErrorCode.SERVER_ERROR) }
  }
}
