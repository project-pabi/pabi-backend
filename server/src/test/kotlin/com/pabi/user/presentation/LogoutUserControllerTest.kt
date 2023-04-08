package com.pabi.user.presentation

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.pabi.common.CustomDescribeSpec
import com.pabi.common.IntegrationTest
import io.mockk.coEvery
import io.mockk.mockk
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.security.Principal

@IntegrationTest
class LogoutUserControllerTest(
    private val mockMvc: MockMvc,
) : CustomDescribeSpec() {

    private val logOutUrl = "/api/v1/user/log-out"
    private val objectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    val principal: Principal = mockk()

    init {
        describe("#LogOutUserController") {

            context("유효한 엑세스 토큰인 경우") {
                // given
                val request = SignUpUserDto.SignUpUserRequest(
                    email = "logout@test.com",
                    password = "validPassword1!",
                    nickName = "validNickname"
                )
                val requestAccessToken =
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJsb2dvdXRAdGVzdC5jb20iLCJuYW1lIjoibG9nb3V0QHRlc3QuY29tIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjUwMDAwMDAwMDB9.xsy7LSdzwzlRrei9feh9zExdBiLgTmv7zzGpYW4qTaQ"

                coEvery { principal.name } returns "logout@test.com"

                mockMvc.perform(
                    post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )

                it("로그아웃 성공") {
                    // when
                    val result = mockMvc.perform(
                        post(logOutUrl)
                            .header(
                                HttpHeaders.AUTHORIZATION,
                                requestAccessToken
                            )
                            .contentType(MediaType.APPLICATION_JSON)
                    )

                    // then
                    result
                        .andExpect(status().isOk)
                        .andReturn()
                }
            }
            context("유효한 엑세스 토큰이 아닌 경우") {
                // given

                it("로그아웃 실패") {
                    // when
                    val result = mockMvc.perform(
                        post(logOutUrl)
                            .header(
                                HttpHeaders.AUTHORIZATION,
                                "fail token"
                            )
                            .contentType(MediaType.APPLICATION_JSON)
                    )

                    // then
                    result
                        .andExpect(status().isUnauthorized)
                        .andReturn()
                }
            }
        }
    }
}
