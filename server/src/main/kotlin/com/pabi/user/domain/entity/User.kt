package com.pabi.user.domain.entity

import com.pabi.common.exception.InvalidPasswordException
import com.pabi.common.exception.WithdrawalUserException
import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserCommand
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(unique = true)
    var uuid: String = UUID.randomUUID().toString(),

    @Column(unique = true)
    var email: String = "",

    @Column(unique = true)
    var nickName: String = "",

    var password: String = "",

    @Column(columnDefinition = "boolean default false")
    var withdrawal: Boolean = false,

    var roles: String = "",
) {

    fun signIn(password: String) {
        if (this.password != password) {
            throw InvalidPasswordException()
        }

        if (this.withdrawal) {
            throw WithdrawalUserException()
        }
    }

    fun modifyUser(request: ModifyUserCommand) {
        nickName = request.nickName
    }
}
