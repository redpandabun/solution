package com.mysolution

import com.fasterxml.jackson.databind.ObjectMapper
import com.mysolution.common.serialize.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod.ALWAYS
import org.springframework.context.ApplicationContext
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertSame

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
@SpringBootTest(useMainMethod = ALWAYS)
internal class MySolutionIntegrationTest {

  @Autowired
  lateinit var objectMapper: ObjectMapper

  @Test
  fun contextLoads(ctx: ApplicationContext) {
    assertNotNull(ctx.id)
  }

  @Test
  fun `빈에 등록된 objectMapper 는 동일해야 한다`() {
    assertSame(Json.mapper, objectMapper)
  }
}
