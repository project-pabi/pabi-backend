package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidTokenException
import com.pabi.common.jwt.TokenProvider
import com.pabi.common.redis.RedisRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import java.time.Instant
import java.util.Date

class LogOutUserServiceTest : DescribeSpec({

    val tokenProvider: TokenProvider = mockk()
    val redisRepository: RedisRepository = mockk()
    val logOutUserService = LogOutUserService(tokenProvider, redisRepository)

    describe("#LogOutUser") {
        context("정상적인 엑세스 토큰이 아닌 경우") {
            val email = "test@naver.com"
            val requestAccessToken = "testFailToken"
            coEvery { tokenProvider.resolveToken(requestAccessToken) } returns null

            it("실패한다") {
                val exception = shouldThrow<InvalidTokenException> {
                    logOutUserService.logOutUser(email, requestAccessToken)
                }
                exception.message shouldBe InvalidTokenException().message
            }
        }
        context("정상적인 엑세스 토큰인 경우") {
            val email = "test@naver.com"
            val requestAccessToken = "Bearer testToken"
            val accessToken = "testToken"
            coEvery { tokenProvider.resolveToken(requestAccessToken) } returns "testToken"
            coEvery { tokenProvider.getExpirationFromToken(accessToken) } returns Date.from(Instant.now())
            coEvery { redisRepository.REFRESH_PREFIX } returns "RT:"
            coEvery { redisRepository.ACCESS_BLACK } returns "ATB:"
            coJustRun { redisRepository.delValue(any()) }
            coJustRun { redisRepository.setValue(any(), any(), any(), any()) }

            it("성공한다") {
                val result = logOutUserService.logOutUser(email, requestAccessToken)
            }
        }
    }
})
