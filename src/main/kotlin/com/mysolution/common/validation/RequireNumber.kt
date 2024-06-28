package com.mysolution.common.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

/**
 * 지정된 값에 하나 이상의 숫자가 포함되어 있는지 확인하는 유효성 검사 어노테이션
 *
 * 사용법:
 *   - 유효성을 검사해야 하는 필드 또는 클래스에 이 주석을 추가한다
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Target(FIELD, CLASS)
@Retention(RUNTIME)
@Constraint(validatedBy = [RequireNumber.Validator::class])
annotation class RequireNumber(
  val message: String = "The string must contain at least one number",
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload>> = [],
) {
  class Validator : ConstraintValidator<RequireNumber, String> {

    companion object {
      private val regex = Regex("[0-9]")
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
      return value
        ?.contains(regex)
        ?: false
    }
  }
}
