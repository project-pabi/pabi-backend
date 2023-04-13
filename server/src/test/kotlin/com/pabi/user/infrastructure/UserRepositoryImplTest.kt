package com.pabi.user.infrastructure

import com.pabi.common.IntegrationTest
import com.pabi.common.exception.NotFoundUserException
import com.pabi.user.domain.entity.User
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

@IntegrationTest
class UserRepositoryImplTest(
    private val userRepositoryImpl: UserRepositoryImpl,
) : DescribeSpec({

    describe("#save") {
        it("수정 테스트 성공") {
            val user = User()
            userRepositoryImpl.save(user)
            user.nickName = "test nickname"
            val result = userRepositoryImpl.save(user)

            result.nickName shouldBe "test nickname"
        }

        it("저장 테스트 성공") {
            val user = User(email = "test151@test.com")
            val result = userRepositoryImpl.save(user)

            result shouldBe user
        }
    }
    describe("#find") {
        it("조회 테스트 성공") {
            val user = User(
                email = "test4@test4.com",
                nickName = "getTest",
                rating = 2.2F
            )
            val save = userRepositoryImpl.save(user)

            val result = save.id?.let { userRepositoryImpl.findByIdAndWithdrawalFalse(it) }
            result shouldBe save
        }
        it("조회 테스트 실패") {
            val userId = 99999999999L
            val result = userRepositoryImpl.findByIdAndWithdrawalFalse(userId)
            result shouldBe null
        }
    }
})
