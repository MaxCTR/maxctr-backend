package com.maxctr.projectx.dao.users

import com.maxctr.projectx.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User
}