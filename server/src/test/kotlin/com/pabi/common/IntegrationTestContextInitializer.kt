package com.pabi.common

import com.github.dockerjava.api.model.PortBinding
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class IntegrationTestContextInitializer :
    ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val DATABASE_NAME = "pabi"
        val USERNAME = "pabi-user"
        val PASSWORD = "pabi-pw"

        DockerImageName.parse("postgres:14")
            .asCompatibleSubstituteFor("postgres")
            .let { compatibleImageName ->
                PostgreSQLContainer<Nothing>(compatibleImageName)
            }
            .apply {
                withDatabaseName(DATABASE_NAME)
                withUsername(USERNAME)
                withPassword(PASSWORD)
                withEnv("POSTGRES_USER", USERNAME)
                withEnv("POSTGRES_PASSWORD", PASSWORD)
                withCreateContainerCmdModifier {
                    it.withPortBindings(PortBinding.parse("54321:5432"))
                }
                start()
            }

        val REDIS_CONTAINER =
            GenericContainer(
                DockerImageName.parse("redis:5.0.3-alpine")
            ).withCreateContainerCmdModifier {
                it.withPortBindings(PortBinding.parse("6380:6379"))
            }

        REDIS_CONTAINER.start()

        System.setProperty("spring.redis.host", REDIS_CONTAINER.host)
        System.setProperty("spring.redis.port", REDIS_CONTAINER.getMappedPort(6379).toString())
    }
}
