package com.pabi.user.presentation

import FindUserDto
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.pabi.common.CustomDescribeSpec
import com.pabi.common.IntegrationTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.MultiValueMap

@IntegrationTest
class FindUserControllerTest(
    private val mockMvc: MockMvc,
) : CustomDescribeSpec() {

    private val findUrl = "/api/v1/user/profile"

    init {
        describe("#FindUserControllerTest") {
            context("요청이 유효한 경우") {

                it("유저 정보를 조회 한다") {
                    // when
                    val result = mockMvc.perform(
                        get(findUrl).param("email","test@naver.com")
                    )
                    // then
                    result
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.result").value("SUCCESS"))
                        .andReturn()
                }
            }

            context("요청이 유효하지 않은 경우") {
                it("Bad Request 반환") {
                    // when
                    val result = mockMvc.perform(
                        get(findUrl).param("email","invalid-email")
                    )

                    // then
                    result
                        .andExpect(jsonPath("$.result").value("FAIL"))
                        .andReturn()
                }
            }
        }
    }
}
