package com.security.challenge.services.ports

import reactor.core.publisher.Mono

interface SecurityService {
    fun validatePassword(password: String): Mono<Boolean>
}