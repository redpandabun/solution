package com.mysolution.user.exception

/**
 * 사용자가 존재하지 않을 때 발생하는 예외
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
class UserNotFoundException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)
