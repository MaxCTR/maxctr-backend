package com.maxctr.projectx.http.rest.auth

import com.maxctr.projectx.dto.auth.UserAuthDto
import com.maxctr.projectx.services.auth.AuthService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import io.swagger.annotations.Api

import javax.validation.Valid


@Api(tags = ["Authentication"])
@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
class AuthController {
    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody request: UserAuthDto): ResponseEntity<*> = authService.authenticate(request)

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody request: UserAuthDto): ResponseEntity<*> = authService.register(request)
}
