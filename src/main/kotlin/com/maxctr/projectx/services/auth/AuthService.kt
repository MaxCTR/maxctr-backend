package com.maxctr.projectx.services.auth

import com.maxctr.projectx.dao.users.RoleRepository
import com.maxctr.projectx.dao.users.UserRepository
import com.maxctr.projectx.domain.users.User
import com.maxctr.projectx.dto.auth.UserAuthDto
import com.maxctr.projectx.http.models.HttpMessage
import com.maxctr.projectx.security.JWT
import com.maxctr.projectx.security.JWTProvider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.util.*
import java.util.stream.Collectors

@Service
class AuthService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

    @Autowired
    lateinit var tokenProvider: JWTProvider

    fun authenticate(request: UserAuthDto): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByEmail(request.email!!)

        return if (userCandidate.isPresent) {
            val user: User = userCandidate.get()
            val authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(request.email, request.password)
            )

            SecurityContextHolder.getContext().authentication = authentication

            val jwt: String = tokenProvider.createToken(user.email!!)

            val authorities: List<GrantedAuthority> = user.roles!!
                    .stream()
                    .map { role -> SimpleGrantedAuthority(role.name) }
                    .collect(Collectors.toList<GrantedAuthority>())

            ResponseEntity(JWT(jwt, user.email, authorities), HttpStatus.OK)
        } else {
            ResponseEntity(HttpMessage("User not found!"), HttpStatus.BAD_REQUEST)
        }
    }

    fun register(request: UserAuthDto): ResponseEntity<*> {
        val userCandidate: Optional <User> = userRepository.findByEmail(request.email!!)

        return if (!userCandidate.isPresent) {
            val user = User(UUID.randomUUID(), request.email!!, encoder.encode(request.password),true)

            user.roles = listOf(roleRepository.findByName("ROLE_USER"))

            userRepository.save(user)

            ResponseEntity(HttpMessage("User registered successfully!"), HttpStatus.OK)
        } else {
            ResponseEntity(HttpMessage("User already exists!"), HttpStatus.BAD_REQUEST)
        }
    }

}