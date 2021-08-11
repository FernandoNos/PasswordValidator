package com.security.challenge.services

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.lang.RuntimeException

class SecurityServiceTest {

    @InjectMocks
    private lateinit var securityService: SecurityServiceImpl

    @Mock
    private lateinit var passwordValidationService: PasswordValidationServiceImpl

    @BeforeEach
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `security service - SUCCESS - password validation returns true` (){
        Mockito.`when`(passwordValidationService.validatePassword("123")).thenReturn(Mono.just(true))

        StepVerifier.create(securityService.validatePassword("123"))
            .expectNextMatches { result -> result }
            .verifyComplete()
    }

    @Test
    fun `security service - SUCCESS - password validation returns false` (){
        Mockito.`when`(passwordValidationService.validatePassword("123")).thenReturn(Mono.just(false))

        StepVerifier.create(securityService.validatePassword("123"))
            .expectNextMatches { result -> !result }
            .verifyComplete()
    }

    @Test
    fun `security service - ERROR - password validation throws exception` (){

        given(passwordValidationService.validatePassword("123")).willAnswer { Mono.error<Exception>(Exception("Test")) }

        StepVerifier.create(securityService.validatePassword("123"))
            .expectErrorMatches {it is RuntimeException && it.message.equals("Test")}
            .verify()
    }
}