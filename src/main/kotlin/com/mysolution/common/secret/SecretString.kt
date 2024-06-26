package com.mysolution.common.secret

/**
 * 외부로 노출되면 안되는 문자열 클래스
 *
 * 내부적으로 [toString]을 오버라이드하여 노출을 최소화한다.
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@JvmInline
value class SecretString(val value: String) {
  override fun toString(): String = "******"
}
