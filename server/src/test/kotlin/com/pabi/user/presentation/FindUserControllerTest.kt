package com.pabi.user.presentation

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import com.pabi.common.CustomDescribeSpec
import com.pabi.common.IntegrationTest
import com.pabi.user.domain.repository.UserRepository
import com.pabi.user.domain.service.SignUpUserService
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@IntegrationTest
class FindUserControllerTest(
    private val mockMvc: MockMvc,
) : CustomDescribeSpec() {

    private val findUrl = "/api/v1/users"
    private final val userRepository: UserRepository = mockk()
    val signUpUserService = SignUpUserService(userRepository)
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    init {
        describe("#FindUserControllerTest") {
            context("요청이 유효하지 않은 경우") {
                it("Bad Request 반환") {
                    // when
                    val result = mockMvc.perform(
                        get("$findUrl/99999")
                    )

                    // then
                    result
                        .andExpect(jsonPath("$.result").value("FAIL"))
                        .andReturn()
                }
            }

            context("요청한 유저 객체가 없을 경우") {
                it("익셉션 반환") {
                    // when
                    val userId = 999999999898L
                    val result2 = mockMvc.perform(
                        get("$findUrl/$userId")
                    )
                    // then
                    result2
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.errorCode").value("NotFoundUserException"))
                        .andReturn()
                }
            }

            context("요청이 유효한 경우") {
                it("유저 정보를 조회 한다") {
                    // given

                    val request = com.pabi.user.presentation.SignUpUserDto.SignUpUserRequest(
                        email = "test@test.com",
                        password = "validPassword1!",
                        nickName = "testNicname"
                    )

                    val result1 = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )

                    val contentAsString = result1.andReturn().response.contentAsString

                    val userId = JsonPath.parse(contentAsString).read<Int>("$.data.id").toString()

                    // when
                    val result2 = mockMvc.perform(
                        get("$findUrl/$userId")
                    )

                    // then
                    result2
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.result").value("SUCCESS"))
                        .andReturn()
                }
            }
        }
    }
}
