package com.mysolution.common.error.exceptionhandler

import com.mysolution.common.error.exceptionhandler.provider.BusinessExceptionErrorHandlerProvider
import com.mysolution.common.error.exceptionhandler.provider.DefaultErrorExceptionHandlerProvider
import com.mysolution.common.error.exceptionhandler.provider.HttpMessageNotReadableExceptionHandlerProvider
import com.mysolution.common.error.exceptionhandler.provider.ServletExceptionErrorHandlerProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order

/**
 * [ErrorExceptionHandler]를 등록하기 위한 스프링 [Configuration] 클래스
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Configuration(proxyBeanMethods = false)
class ErrorExceptionHandlerBeanConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  fun businessExceptionHandlerProvider() = BusinessExceptionErrorHandlerProvider

  @Bean
  fun httpMessageNotReadableExceptionHandlerProvider() = HttpMessageNotReadableExceptionHandlerProvider

  @Bean
  fun servletExceptionHandlerProvider() = ServletExceptionErrorHandlerProvider

  @Bean
  @Order(LOWEST_PRECEDENCE)
  fun defaultExceptionHandlerProvider() = DefaultErrorExceptionHandlerProvider
}
