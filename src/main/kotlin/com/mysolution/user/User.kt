package com.mysolution.user

import com.mysolution.common.secret.SecretString
import jakarta.persistence.*
import jakarta.persistence.GenerationType.SEQUENCE

/**
 * 사용자 엔티티
 *
 * @property id 사용자 식별자
 * @property username 사용자 계졍
 * @property encryptedPassword 암호화된 사용자 비밀번호
 * @author RedPandaBun
 * @since 0.1.0
 */
@Entity
@Table(name = "my_user")
@SequenceGenerator(name = "id_generator__my_user", initialValue = 1001, allocationSize = 1)
class User(

  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = SEQUENCE, generator = "id_generator__my_user")
  val id: Long = 0,

  @Column(name = "username", updatable = false)
  val username: String,

  @Column(name = "encrypted_password")
  var encryptedPassword: SecretString
)
