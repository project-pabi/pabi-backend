package com.pabi.user.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.pabi.common.CustomDescribeSpec
import com.pabi.common.IntegrationTest
import com.pabi.common.response.CommonResponse
import com.pabi.user.presentation.SignUpUserDto.SignUpUserRequest
import io.kotest.matchers.shouldBe
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@IntegrationTest
class SignUpUserControllerTest(
    private val mockMvc: MockMvc,
) : CustomDescribeSpec() {

    private val signUpUrl = "/api/v1/user"
    private val objectMapper = ObjectMapper()

    init {
        describe("SignUpUserController 테스트") {
            context("요청이 유효하지 않은 경우") {
                val request = SignUpUserRequest(
                    email = "invalid-email",
                    password = "pass",
                    nickName = "nick"
                )

                it("Bad Request 반환") {
                    val result = mockMvc.perform(
                        post(signUpUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )
                        .andExpect(status().isBadRequest)
                        .andReturn()

                    val response = objectMapper.readValue(
                        result.response.contentAsString,
                        CommonResponse::class.java
                    ) as CommonResponse<*>

                    response.data.shouldBe(null)
                    result.response.status shouldBe 400
                }
            }

            context("요청이 유효한 경우") {
                val request = SignUpUserRequest(
                    email = "valid-email@test.com",
                    password = "validPassword1!",
                    nickName = "validNickname"
                )

                it("새 유저를 생성한다") {
                    mockMvc.perform(
                        post(signUpUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )
                        .andExpect(status().isOk)
                }
            }
        }
    }
}
