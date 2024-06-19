package com.mysolution.common.logging

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
internal class CreateLoggerTest {

  @Test
  fun `createLogger 는 올바른 클래스 이름을 반환해야 한다`() {
    val logger = createLogger { }
    assertEquals(this::class.qualifiedName, logger.name)
  }
}
