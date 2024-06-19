package com.mysolution.common.web.error

import com.mysolution.common.error.exceptionhandler.ErrorExceptionHandler
import com.mysolution.common.web.WebUtils.writeError
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.filter.OrderedFilter
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * 서블릿 필터에서 예외가 발생하는 경우 공통 예외 응답을 제공하기 위한 필터
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Component
class WebExceptionHandlerFilter(private val handler: ErrorExceptionHandler) : OncePerRequestFilter(), OrderedFilter {
  override fun getOrder() = -104 // requestContextFilter 뒤에 위치하도록 설정

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    try {
      filterChain.doFilter(request, response)
    } catch (e: Exception) {
      val error = handler.handle(e)
      return response.writeError(error)
    }
  }
}
