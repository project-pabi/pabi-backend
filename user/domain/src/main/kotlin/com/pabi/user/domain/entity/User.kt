package com.pabi.user.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(unique = true)
    var uuid: String = UUID.randomUUID().toString(),

    @Column(unique = true)
    var email: String,

    @Column(unique = true)
    var nickName: String,

    var password: String,

    @Column(columnDefinition = "boolean default false")
    var withdrawal: Boolean = false,
)