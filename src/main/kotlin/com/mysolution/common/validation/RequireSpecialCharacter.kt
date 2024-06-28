package com.mysolution.common.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass
import kotlin.text.RegexOption.IGNORE_CASE

/**
 * 문자열에 하나 이상의 특수 문자가 포함되어 있는지 확인하는 사용자 정의 주석입니다
 *
 * @param allow 허용할 특수문자 목록, 비어있으면 모든 특수문자를 허용한다
 * @author RedPandaBun
 * @since 0.1.0
 */
@Target(FIELD, CLASS)
@Retention(RUNTIME)
@Constraint(validatedBy = [RequireSpecialCharacter.Validator::class])
annotation class RequireSpecialCharacter(
  val allow: CharArray = ['!', '@', '#', '$', '%', '^', '&', '*', '-', '+', '_', '~', '.'],
  val message: String = "The string must contain at least one special character",
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload>> = [],
) {

  class Validator : ConstraintValidator<RequireSpecialCharacter, String> {
    companion object {
      // '[^a-zA-Z0-9]'는 영숫자를 의미, 영숫자가 아닌 '%s'에 사용자 지정 특수 문자 삽입
      private const val SPECIAL_CHARACTER_PATTERN_TEMPLATE = "[^a-z0-9%s]"
    }

    private lateinit var regex: Regex

    override fun initialize(annotation: RequireSpecialCharacter) {
      val allowCharacters = annotation.allow.takeIf { it.isNotEmpty() }?.joinToString("") ?: ""
      regex = String.format(SPECIAL_CHARACTER_PATTERN_TEMPLATE, allowCharacters).toRegex(IGNORE_CASE)
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
      return value
        ?.contains(regex)
        ?: false
    }
  }
}
