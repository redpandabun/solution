package com.mysolution.user

import com.mysolution.user.model.CreateUserSpec
import org.springframework.web.bind.annotation.*

/**
 * @author RedPandaBun
 * @since 0.1.0
 */
@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {

  @GetMapping("/{id}")
  fun findUser(@PathVariable id: Long) = service.findUser(id)

  @PostMapping
  fun createUser(@RequestBody spec: CreateUserSpec) = service.createUser(spec)
}
