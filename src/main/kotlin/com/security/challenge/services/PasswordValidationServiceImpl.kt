package com.security.challenge.services

import com.security.challenge.services.ports.PasswordValidationService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class PasswordValidationServiceImpl(
        @Value("\${challenge.security.password.regex-pattern}")
        var passwordPattern: String) : PasswordValidationService
{

    override fun validatePassword(password: String): Mono<Boolean>{
        return Mono.just(Regex(passwordPattern))
            .map { it.matches(password) }
    }

}