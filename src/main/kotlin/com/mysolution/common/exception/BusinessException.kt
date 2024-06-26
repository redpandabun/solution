package com.mysolution.common.exception

import com.mysolution.common.error.ErrorInfo

/**
 * My solution 비지니스 예외 클래스
 *
 * 모든 비지니스 예외는 이 클래스를 상속받아 구현해야 한다
 *
 * @property error 오류 정보
 * @author RedPandaBun
 * @since 0.1.0
 */
abstract class BusinessException(
  open val error: ErrorInfo,
  override val message: String? = null,
  override val cause: Throwable? = null
) : RuntimeException(message, cause)
