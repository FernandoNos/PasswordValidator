package com.security.challenge.services

import com.security.challenge.services.ports.PasswordValidationService
import com.security.challenge.services.ports.SecurityService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.onErrorMap
import java.lang.RuntimeException

@Component
class SecurityServiceImpl(private val passwordValidationServiceImpl: PasswordValidationService) : SecurityService {

    private val logger = LoggerFactory.getLogger(SecurityServiceImpl::class.java)

    override fun validatePassword(password: String): Mono<Boolean> {
        return passwordValidationServiceImpl.validatePassword(password)
            .doOnError { logger.error("Error validating password $it") }
            .onErrorMap { RuntimeException(it?.message) }
    }
}