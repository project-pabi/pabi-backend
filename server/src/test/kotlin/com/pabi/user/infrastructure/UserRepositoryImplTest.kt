package com.pabi.user.infrastructure

import com.pabi.common.IntegrationTest
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
        //
        // it("조회 테스트 성공") {
        //     val email = "test3@test3.com"
        //     val user = User(email = email)
        //     userRepositoryImpl.save(user)
        //
        //     val result = userRepositoryImpl.findUserProfile(email)
        //
        //     result shouldBe user
        // }
    }

})
