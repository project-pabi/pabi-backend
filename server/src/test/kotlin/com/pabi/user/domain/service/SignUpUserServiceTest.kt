package com.pabi.user.domain.service

import com.pabi.common.exception.DuplicateUserEmailException
import com.pabi.common.exception.DuplicateUserNickNameException
import com.pabi.user.domain.dto.SignUpUserDto.SignUpUserCommand
import com.pabi.user.domain.entity.User
import com.pabi.user.domain.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class SignUpUserServiceTest : DescribeSpec({

    val userRepository: UserRepository = mockk()
    val signUpUserService = SignUpUserService(userRepository)

    describe("#signUpUser") {
        context("닉네임이 존재하는 경우") {
            val nickname = "test nickname"
            val user = User(
                nickName = nickname
            )
            val request = SignUpUserCommand(
                nickName = nickname
            )
            coEvery { userRepository.findByNickNameOrEmail(nickname, any()) } returns user

            it("실패한다") {
                val exception = shouldThrow<DuplicateUserNickNameException> {
                    signUpUserService.signUpUser(request)
                }
                exception.message shouldBe DuplicateUserNickNameException().message
            }
        }

        context("이메일이 존재하는 경우") {
            val email = "test@test.com"
            val user = User(
                nickName = "1",
                email = email
            )
            val request = SignUpUserCommand(
                nickName = "2",
                email = email
            )
            coEvery { userRepository.findByNickNameOrEmail(any(), email) } returns user

            it("실패한다") {
                val exception = shouldThrow<DuplicateUserEmailException> {
                    signUpUserService.signUpUser(request)
                }
                exception.message shouldBe DuplicateUserEmailException().message
            }
        }

        context("유효한 요청이면") {
            val email = "test@test.com"
            val user = User(
                nickName = "1",
                email = email
            )
            val newNickname = "2"
            val newEmail = "new@test.com"
            val request = SignUpUserCommand(
                nickName = newNickname,
                email = newEmail
            )
            val newUser = User(
                id = 32,
                nickName = newNickname,
                email = newEmail
            )
            coEvery { userRepository.findByNickNameOrEmail(newNickname, newEmail) } returns user
            coEvery { userRepository.save(any()) } returns newUser

            it("성공한다") {
                val result = signUpUserService.signUpUser(request)

                result.id shouldBe newUser.id
                result.email shouldBe newUser.email
                result.nickName shouldBe newUser.nickName
            }
        }
    }
})
