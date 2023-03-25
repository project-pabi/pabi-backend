package com.pabi.common

import com.pabi.server.PabiBackendApplication
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [PabiBackendApplication::class])
@Testcontainers
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [IntegrationTestContextInitializer::class])
annotation class IntegrationTest
