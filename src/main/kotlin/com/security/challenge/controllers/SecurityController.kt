package com.security.challenge.controllers

import com.security.challenge.controllers.dto.PasswordValidationDTO
import com.security.challenge.services.ports.SecurityService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("security")
class SecurityController(private val securityServiceImpl: SecurityService) {

    @PostMapping("/password/validate")
    fun validatePassword(@RequestBody @Valid passwordValidationRequest: PasswordValidationDTO): Mono<Boolean> {
        return this.securityServiceImpl.validatePassword(passwordValidationRequest.password)
    }
}