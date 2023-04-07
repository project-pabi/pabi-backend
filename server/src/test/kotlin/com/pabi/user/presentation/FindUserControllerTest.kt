package com.pabi.user.presentation

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
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

    private val findUrl = "/api/v1/user/profile"
    private final val userRepository: UserRepository = mockk()
    val signUpUserService = SignUpUserService(userRepository)
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    init {
        describe("#FindUserControllerTest") {
            context("요청이 유효하지 않은 경우") {
                it("Bad Request 반환") {
                    // when
                    val result = mockMvc.perform(
                        get(findUrl).param("email", "test@test.com")
                    )

                    // then
                    result
                        .andExpect(jsonPath("$.result").value("FAIL"))
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

                    mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )

                    // when
                    val result = mockMvc.perform(
                        get(findUrl).param("email", "test@test.com")
                    )

                    // then
                    result
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.result").value("SUCCESS"))
                        .andReturn()
                }
            }

        }
    }
}
