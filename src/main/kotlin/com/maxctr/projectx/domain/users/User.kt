package com.maxctr.projectx.domain.users

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: UUID? = UUID.randomUUID(),

        @Column(name = "email", unique = true)
        var email: String? = null,

        @Column(name = "password")
        var password: String? = null,

        @Column(name = "enabled")
        var enabled: Boolean = false,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Collection<Role>? = null,

        @OneToOne(mappedBy = "user")
        var account: Profile? = null
)