package com.mysolution.common.web

import com.mysolution.common.serialize.Json
import com.mysolution.common.web.error.WebExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Spring Web MVC 설정
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Configuration(proxyBeanMethods = false)
class WebMvcConfig(private val exceptionResolver: WebExceptionHandler) : WebMvcConfigurer {

  override fun configureHandlerExceptionResolvers(resolvers: MutableList<HandlerExceptionResolver>) {
    resolvers.add(exceptionResolver)
  }

  override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    val jsonConverter = MappingJackson2HttpMessageConverter(Json.mapper)

    converters.add(jsonConverter)
  }
}
