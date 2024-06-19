package com.mysolution.common.logging

import io.github.oshai.kotlinlogging.KotlinLogging

/**
 * 선언된 위치의 클래스 이름으로 로거 인스턴스를 생성한다
 *
 * @param func 클래스 이름 추론을 위한 람다 함수, 실제로 호출되지 않는다
 * @author RedPandaBun
 * @since 0.1.0
 */
fun createLogger(func: () -> Unit) = KotlinLogging.logger(func)
