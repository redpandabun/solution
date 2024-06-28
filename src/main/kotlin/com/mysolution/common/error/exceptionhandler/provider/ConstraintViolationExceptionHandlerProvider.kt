package com.mysolution.common.error.exceptionhandler.provider

import com.mysolution.common.error.ErrorCode.VALIDATION_FAILED
import com.mysolution.common.error.ErrorInfo
import com.mysolution.common.secret.SecretString
import jakarta.validation.ConstraintViolationException

/**
 * [ConstraintViolationException]를 처리하기 위한 [ErrorExceptionHandlerProvider]
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
object ConstraintViolationExceptionHandlerProvider : ErrorExceptionHandlerProvider {
  private val IGNORE_ATTRIBUTE_KEYS = setOf("groups", "payload", "message") // jakarta.validation 기본 속성

  override fun canHandle(exception: Exception) = exception is ConstraintViolationException

  override fun handle(exception: Exception): ErrorInfo {
    if (exception !is ConstraintViolationException) {
      throw IllegalArgumentException("This provider can only handle ConstraintViolationException")
    }

    val details = exception.constraintViolations
      .map {
        val attributes = it.constraintDescriptor.attributes
          .filterKeys { key -> !IGNORE_ATTRIBUTE_KEYS.contains(key) }
          .mapValues { (_, v) -> humanFriendlyAttributesValue(v) }

        mapOf(
          "property" to it.propertyPath.toList().last().name,
          "type" to it.constraintDescriptor.annotation.annotationClass.simpleName,
        ).plus(attributes)
      }

    return ErrorInfo(
      code = VALIDATION_FAILED,
      errors = details
    )
  }

  /**
   * 주어진 값을 사람에게 친숙한 방식으로 나타내도록 변환한다
   *
   * @param value 변환 할 값
   * @return 변환된 값
   */
  private fun humanFriendlyAttributesValue(value: Any?): Any? = when (value) {
    is SecretString -> "******" // 민감정보 마스킹
    is CharArray -> value.joinToString(",")
    else -> value
  }
}
