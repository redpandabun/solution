package com.mysolution.common.infrastructure

import com.mysolution.MySolution
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * Spring data JPA 설정
 *
 * @author RedPandaBun
 * @since 0.1.0
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackageClasses = [MySolution::class])
@EnableJpaRepositories(basePackageClasses = [MySolution::class])
class JpaConfig
