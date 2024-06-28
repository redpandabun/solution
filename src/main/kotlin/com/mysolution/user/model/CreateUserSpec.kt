package com.mysolution.user.model

import com.mysolution.common.secret.SecretString
import com.mysolution.user.validation.RawPassword
import com.mysolution.user.validation.Username

/**
 * 사용자 생성 DTO
 *
 * 신규 사용자를 추가하기 위한 정보를 담는 DTO
 *
 * @property username 추가 할 사용자 계정
 * @property password 추가 할 사용자 평문 비밀번호
 * @author RedPandaBun
 * @since 0.1.0
 */
data class CreateUserSpec(
  @Username
  val username: String,

  @RawPassword
  val password: SecretString
)
