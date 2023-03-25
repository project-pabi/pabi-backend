package com.pabi.user.presentation

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.pabi.common.CustomDescribeSpec
import com.pabi.common.IntegrationTest
import com.pabi.user.presentation.SignUpUserDto.SignUpUserRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@IntegrationTest
class SignUpUserControllerTest(
    private val mockMvc: MockMvc,
) : CustomDescribeSpec() {

    private val signUpUrl = "/api/v1/user"
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    init {
        describe("#SignUpUserController") {
            context("요청이 유효하지 않은 경우") {
                // given
                val request = SignUpUserRequest(
                    email = "invalid-email",
                    password = "pass",
                    nickName = "nick"
                )

                it("Bad Request 반환") {
                    // when
                    val result = mockMvc.perform(
                        post(signUpUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )

                    // then
                    result
                        .andExpect(status().isBadRequest)
                        .andReturn()
                }
            }

            context("요청이 유효한 경우") {
                // given
                val request = SignUpUserRequest(
                    email = "valid-email@test.com",
                    password = "validPassword1!",
                    nickName = "validNickname"
                )

                it("새 유저를 생성한다") {
                    // when
                    val result = mockMvc.perform(
                        post(signUpUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )

                    // then
                    result
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.result").value("SUCCESS"))
                        .andReturn()
                }
            }

            context("이미 회원가입한 유저의 요청인 경우") {
                // given
                val request = SignUpUserRequest(
                    email = "valid-email@test.com",
                    password = "validPassword1!",
                    nickName = "validNickname"
                )

                it("새 유저 생성에 실패한다") {
                    // when
                    val result = mockMvc.perform(
                        post(signUpUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )

                    // then
                    result
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.result").value("FAIL"))
                        .andReturn()
                }
            }
        }
    }
}
