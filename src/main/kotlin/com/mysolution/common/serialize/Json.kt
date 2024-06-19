package com.mysolution.common.serialize

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.util.StdDateFormat

/**
 * Json 직렬화 유틸리티
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
object Json {
  val mapper = JsonMapper().apply {
    findAndRegisterModules()

    dateFormat = StdDateFormat.instance
    propertyNamingStrategy = LOWER_CAMEL_CASE
    setDefaultPropertyInclusion(NON_NULL)

    disable(FAIL_ON_UNKNOWN_PROPERTIES)
    disable(WRITE_DATES_AS_TIMESTAMPS)
    disable(WRITE_DURATIONS_AS_TIMESTAMPS)
  }


  /**
   * POJO 객체를 JSON 문자열로 직렬화한다
   *
   * @param pojo 직렬화할 객체
   * @return 직렬화된 JSON 문자열
   */
  fun stringify(pojo: Any): String = mapper.writeValueAsString(pojo)
}
