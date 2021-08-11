package com.security.challenge.services

import com.security.challenge.services.PasswordValidationServiceImpl
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class PasswordValidationServiceTest
{
    private val passwordValidationService = PasswordValidationServiceImpl("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{9,}\$")

    @Test
    fun `password validation - TRUE - valid password`(){
        StepVerifier.create(passwordValidationService.validatePassword("Abcd1234!"))
            .expectNextMatches { result -> result }
            .verifyComplete()
    }

    @Test
    fun `password validation - FALSE - password smaller than minimum size`(){
        StepVerifier.create(passwordValidationService.validatePassword("Abc123!"))
            .expectNextMatches { result -> !result }
            .verifyComplete()
    }

    @Test
    fun `password validation - FALSE - password bigger than minimum size`(){
        StepVerifier.create(passwordValidationService.validatePassword("AbcdE123456!"))
            .expectNextMatches { result -> result }
            .verifyComplete()
    }

    @Test
    fun `password validation - FALSE - password without special character, but valid size`(){
        StepVerifier.create(passwordValidationService.validatePassword("AbcdE1234567"))
            .expectNextMatches { result -> !result }
            .verifyComplete()
    }

    @Test
    fun `password validation - FALSE - password with no letters, but valid size`(){
        StepVerifier.create(passwordValidationService.validatePassword("1234561234567!"))
            .expectNextMatches { result -> !result }
            .verifyComplete()
    }

    @Test
    fun `password validation - FALSE - password with no numbers, but valid size`(){
        StepVerifier.create(passwordValidationService.validatePassword("abcdefgh!"))
            .expectNextMatches { result -> !result }
            .verifyComplete()
    }
}