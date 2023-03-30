package com.pabi.user.domain.service

import com.pabi.common.exception.NotFoundUserEmailException
import com.pabi.user.domain.dto.FindUserDto
import com.pabi.user.domain.entity.User
import com.pabi.user.domain.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class FindUserServiceTest : DescribeSpec({

    val userRepository: UserRepository = mockk()
    val findUserService = FindUserService(userRepository)

    describe("#FindUser") {
        context("유효한 요청일 경우") {
            // given
            val email = "test@test.com"
            val nickName = "mr.kim"
            val rating = 3.2F
            val profileComment = "hi, i'm mr.kim"

            val user = User(
                email = email,
                nickName = nickName,
                rating = rating,
                profileComment = profileComment
            )

            val request = FindUserDto.FindUserCommand(
                email = email
            )

            coEvery { userRepository.findUserProfile(email) } returns user

            it("유저 정보를 조회 한다.") {
                // when
                val result = findUserService.findUser(request)

                result.email shouldBe user.email
                result.nickName shouldBe user.nickName
                result.rating shouldBe user.rating
                result.profileComment shouldBe user.profileComment

                // val exception = shouldThrow<DuplicateUserNickNameException> {
                //     signUpUserService.signUpUser(request)
                // }
                // exception.message shouldBe DuplicateUserNickNameException().message
            }
        }

        context("이메일이 존재하지 않는 경우") {
            val email = "mockkk@test.com"
            val request = FindUserDto.FindUserCommand(
                email = email
            )

            coEvery { userRepository.findUserProfile(email) } returns null

            it("실패한다") {
                val exception = shouldThrow<NotFoundUserEmailException> {
                    findUserService.findUser(request)
                }
                exception.message shouldBe NotFoundUserEmailException().message
            }
        }
    }
})
