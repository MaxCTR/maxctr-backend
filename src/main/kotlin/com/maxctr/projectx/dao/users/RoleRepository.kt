package com.maxctr.projectx.dao.users

import com.maxctr.projectx.domain.users.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role
}