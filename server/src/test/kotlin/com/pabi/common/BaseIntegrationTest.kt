package com.pabi.common

import com.pabi.server.PabiBackendApplication
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootTest(classes = [PabiBackendApplication::class])
@Testcontainers
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [IntegrationTestContextInitializer::class])
abstract class BaseIntegrationTest(body: DescribeSpec.() -> Unit = {}) : DescribeSpec({
    extensions(listOf(SpringTestExtension(SpringTestLifecycleMode.Root)))

    body()
}) {
    @PersistenceContext
    lateinit var em: EntityManager

    override suspend fun afterEach(testCase: TestCase, result: TestResult) {
        em.transaction.begin()
        em.createNativeQuery("DELETE FROM user").executeUpdate()
        em.transaction.commit()
    }
}
