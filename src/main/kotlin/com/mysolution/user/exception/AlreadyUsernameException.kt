package com.mysolution.user.exception

import com.mysolution.common.error.ErrorCode.ALREADY_USERNAME
import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.exception.BusinessException

/**
 * 사용자 생성 시 사용자 계정이 중복되는 경우 발생
 *
 * @property username 중복된 사용자 계정
 * @author RedPandaBun
 * @since 0.1.0
 */
class AlreadyUsernameException(
  private val username: String,
  cause: Throwable? = null
) : BusinessException(
  error = ErrorInfo(
    code = ALREADY_USERNAME,
    errors = "username" to username
  ),
  message = "사용자 계정 '${username}'은 이미 존재하는 계정입니다",
  cause = cause
)
