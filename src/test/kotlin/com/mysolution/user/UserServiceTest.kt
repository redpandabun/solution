package com.mysolution.user

import com.mysolution.common.secret.SecretString
import com.mysolution.user.exception.AlreadyUsernameException
import com.mysolution.user.exception.UserNotFoundException
import com.mysolution.user.model.CreateUserSpec
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
@DataJpaTest
@Import(UserService::class)
@Suppress("SpellCheckingInspection")
class UserServiceTest {

  @Autowired
  lateinit var users: UserRepository

  @Autowired
  lateinit var service: UserService

  @Test
  fun `findUser는 올바르게 사용자를 조회해야 한다`() {
    val savedUser = users.saveAndFlush(
      User(username = "saved-user", encryptedPassword = SecretString("saved-password"))
    )

    val user = service.findUser(savedUser.id)

    assertEquals(savedUser.id, user.id)
    assertEquals(savedUser.username, user.username)
    assertFalse {
      user.toString().contains("password")
    }
  }

  @Test
  fun `findUser는 사용자가 존재하지 않으면 UserNotFoundException이 발생해야 한다`() {
    assertThrows<UserNotFoundException> {
      service.findUser(100)
    }
  }

  @Test
  fun `createUser는 올바르게 사용자 생성을 진행해야 한다`() {
    val spec = CreateUserSpec(username = "test", password = SecretString("password"))

    val user = service.createUser(spec)

    assertTrue { user.id > 0 }
    assertEquals("test", user.username)
  }

  @Test
  fun `createUser는 사용자 계정을 정규화해야 한다`() {
    val spec = CreateUserSpec(username = "TEsTUseR", password = SecretString("password"))

    val user = service.createUser(spec)

    assertEquals("testuser", user.username)
  }

  @Test
  fun `createUser는 중복된 사용자가 저장을 시도하면 AlreadyUsernameException이 발생해야 한다`() {
    users.saveAndFlush(User(username = "saved-user", encryptedPassword = SecretString("saved-password")))

    val spec = CreateUserSpec(username = "saved-user", password = SecretString("saved-user-password"))

    assertThrows<AlreadyUsernameException> {
      service.createUser(spec)
    }
  }
}
