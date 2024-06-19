package com.mysolution.common.web.error

import com.mysolution.common.error.exceptionhandler.ErrorExceptionHandler
import com.mysolution.common.web.WebUtils.writeError
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView

/**
 * 웹 (서블릿) 내부에서 발생하는 예외를 처리하는 클래스
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Component
class WebExceptionHandler(private val handler: ErrorExceptionHandler) : HandlerExceptionResolver {

  override fun resolveException(
    request: HttpServletRequest,
    response: HttpServletResponse,
    ignored: Any?,
    e: Exception
  ): ModelAndView {
    val error = handler.handle(e)

    response.writeError(error)
    return ModelAndView() // 빈 ModelAndView 를 리턴하여 json 응답만 처리하도록 함
  }
}
