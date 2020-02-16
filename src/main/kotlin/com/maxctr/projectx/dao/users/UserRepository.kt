package com.maxctr.projectx.dao.users

import com.maxctr.projectx.domain.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(@Param("email") email: String) : Optional<User>
}