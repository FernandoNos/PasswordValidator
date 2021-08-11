package com.security.challenge.services.ports

import reactor.core.publisher.Mono

interface PasswordValidationService {
    fun validatePassword(password: String): Mono<Boolean>
}