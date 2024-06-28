package com.mysolution.user.validation

import com.mysolution.common.validation.RequireAlphabet
import com.mysolution.common.validation.RequireNumber
import com.mysolution.common.validation.RequireSpecialCharacter
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.CompositionType.AND
import org.hibernate.validator.constraints.ConstraintComposition
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

/**
 * 평문 비밀번호 검증 어노테이션
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = [])
@ConstraintComposition(AND)
@RequireAlphabet
@RequireNumber
@Size(min = 8, max = 256)
@RequireSpecialCharacter
annotation class RawPassword(
  val message: String = "",
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload>> = [],
)
