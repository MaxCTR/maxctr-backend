package com.maxctr.projectx.domain.users

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = UUID.randomUUID(),

        @Column(name = "name")
        val name: String? = null
)