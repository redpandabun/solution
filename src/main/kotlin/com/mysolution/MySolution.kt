package com.mysolution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.runApplication

/**
 * My solution 메인 클래스
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@SpringBootApplication(exclude = [JacksonAutoConfiguration::class])
class MySolution {
  companion object {
    @JvmStatic
    fun main(vararg args: String) {
      runApplication<MySolution>(*args)
    }
  }
}
