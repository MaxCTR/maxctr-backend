package com.maxctr.projectx.domain.users

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "profiles")
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: UUID? = UUID.randomUUID(),

        @OneToOne
        @JoinColumn(name = "email")
        var user: User? = null,

        @Column(name = "first_name")
        var firstName: String? = null,

        @Column(name = "last_name")
        var lastName: String? = null,

        @Column(name = "middle_name")
        var middleName: String? = null
)