package com.mysolution.user

import com.mysolution.user.exception.AlreadyUsernameException
import com.mysolution.user.exception.UserNotFoundException
import com.mysolution.user.model.CreateUserSpec
import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*

/**
 * 사용자 관련 서비스
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Service
@Validated
class UserService(private val users: UserRepository) {

  /**
   * ID로 사용자를 조회한다
   *
   * @param id 조회 할 사용자 ID
   * @return 조회된 사용자
   * @throws UserNotFoundException 사용자가 존재하지 않는 경우
   */
  fun findUser(id: Long): User {
    return users.findByIdOrNull(id)
      ?: throw UserNotFoundException("User not found: $id")
  }

  /**
   * 사용자 생성
   *
   * @param spec 사용자 생성 정보
   * @return 생성된 사용자
   * @throws AlreadyUsernameException 사용자 계정이 중복되는 경우 발생
   */
  fun createUser(@Valid spec: CreateUserSpec): User {
    val lowerUsername = spec.username.lowercase(Locale.ENGLISH)

    // 사용자 계졍 중복 체크
    if (users.existsByUsername(lowerUsername)) {
      throw AlreadyUsernameException(username = lowerUsername)
    }

    val user = User(
      username = lowerUsername,
      encryptedPassword = spec.password // TODO: 비밀번호 암호화
    )

    return users.save(user)
  }
}
