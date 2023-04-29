package com.pabi.user.domain.entity

import com.pabi.common.exception.InvalidPasswordException
import com.pabi.common.exception.WithdrawalUserException
import com.pabi.user.domain.dto.ModifyUserDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserTest : DescribeSpec({

    describe("#signIn") {
        context("유효한 패스워드인 경우") {
            val user = User(
                password = "valid password"
            )

            it("로그인에 성공한다") {
                user.signIn("valid password")
            }
        }

        context("유효하지 않은 패스워드인 경우") {
            val user = User(
                password = "valid password"
            )

            it("로그인에 실패한다") {
                val exception = shouldThrow<InvalidPasswordException> {
                    user.signIn("different password")
                }
                exception.message shouldBe InvalidPasswordException().message
            }
        }

        context("탈퇴한 유저의 경우") {
            val user = User(
                password = "valid password",
                withdrawal = true
            )

            it("로그인에 실패한다") {
                val exception = shouldThrow<WithdrawalUserException> {
                    user.signIn("valid password")
                }
                exception.message shouldBe WithdrawalUserException().message
            }
        }
    }

    describe("#modifyUser") {
        context("요청이 유효한 경우") {
            val user = User(nickName = "old_nickname")

            it("유저 수정에 성공한다") {
                val newNickname = "new_nickname"
                val command = ModifyUserDto.ModifyUserCommand(newNickname)
                user.modifyUser(command)

                user.nickName shouldBe newNickname
            }
        }
    }
})
