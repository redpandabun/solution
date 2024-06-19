package com.mysolution.common.serialize

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
@Configuration(proxyBeanMethods = false)
class SerializeConfig {
  @Bean
  @Primary
  protected fun objectMapper() = Json.mapper
}
