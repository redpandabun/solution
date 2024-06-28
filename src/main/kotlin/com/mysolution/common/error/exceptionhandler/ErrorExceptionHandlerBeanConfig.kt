package com.mysolution.common.error.exceptionhandler

import com.mysolution.common.error.exceptionhandler.provider.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered.HIGHEST_PRECEDENCE
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
  @Order(HIGHEST_PRECEDENCE)
  protected fun businessExceptionHandlerProvider() = BusinessExceptionErrorHandlerProvider

  @Bean
  protected fun constraintViolationExceptionHandlerProvider() = ConstraintViolationExceptionHandlerProvider

  @Bean
  protected fun httpMessageNotReadableExceptionHandlerProvider() = HttpMessageNotReadableExceptionHandlerProvider

  @Bean
  protected fun servletExceptionHandlerProvider() = ServletExceptionErrorHandlerProvider

  @Bean
  @Order(LOWEST_PRECEDENCE)
  protected fun defaultExceptionHandlerProvider() = DefaultErrorExceptionHandlerProvider
}
