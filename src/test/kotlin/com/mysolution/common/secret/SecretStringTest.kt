package com.mysolution.common.secret

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
class SecretStringTest {

  @Test
  fun `toString 메소드는 언제나 마스킹된 값을 반환해야 한다`() {
    val secretString = SecretString("my_secret_string")

    assertEquals("******", secretString.toString())
  }
}
