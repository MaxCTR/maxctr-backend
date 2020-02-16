package com.maxctr.projectx.dao.users

import com.maxctr.projectx.domain.users.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {
}