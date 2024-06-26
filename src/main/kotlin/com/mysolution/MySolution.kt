package com.mysolution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication

/**
 * My solution 메인 클래스
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@SpringBootApplication(
  exclude = [
    JacksonAutoConfiguration::class,  // common.serialize.SerializeConfig 에서 정의
    ErrorMvcAutoConfiguration::class, // common.web.webMvcConfig 에서 정의
  ]
)
class MySolution {
  companion object {
    @JvmStatic
    fun main(vararg args: String) {
      runApplication<MySolution>(*args)
    }
  }
}
