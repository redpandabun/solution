package com.mysolution.user.exception

import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.exception.BusinessException

/**
 * 사용자가 존재하지 않을 때 발생하는 예외
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
class UserNotFoundException(message: String? = null, cause: Throwable? = null) : BusinessException(
  error = ErrorInfo.notFound,
  message,
  cause
)
