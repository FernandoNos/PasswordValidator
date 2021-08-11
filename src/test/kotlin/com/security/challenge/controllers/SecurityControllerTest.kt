package com.security.challenge.controllers

import com.security.challenge.controllers.dto.PasswordValidationDTO
import com.security.challenge.services.ports.SecurityService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest(SecurityController::class)
class SecurityControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var securityService: SecurityService

    @Test
    fun `security controller - SUCCESS - password is not empty`(){

        Mockito.`when`(securityService.validatePassword("123")).thenReturn(Mono.just(true))

        var request = PasswordValidationDTO("123")

        webTestClient.post()
            .uri("/security/password/validate")
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), PasswordValidationDTO::class.java)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(Boolean::class.java)
    }

    @Test
    fun `security controller - SUCCESS - password is empty`(){

        Mockito.`when`(securityService.validatePassword("")).thenReturn(Mono.just(false))

        var request = PasswordValidationDTO("")

        webTestClient.post()
            .uri("/security/password/validate")
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), PasswordValidationDTO::class.java)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(Boolean::class.java)
    }

    @Test
    fun `security controller - BAD REQUEST - no body is provided`(){
        webTestClient.post()
            .uri("/security/password/validate")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isBadRequest
    }
}