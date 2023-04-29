plugins {
    kotlin("plugin.jpa") version "1.7.0"

    id("io.kotest") version "0.3.8"
}

dependencies {
    implementation(project(path = ":common", configuration = "default"))

    runtimeOnly("org.postgresql:postgresql")
    // mysql
    implementation("mysql:mysql-connector-java")

    // persistence
    implementation("javax.persistence:javax.persistence-api:2.2")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.hibernate.validator:hibernate-validator:6.1.2.Final")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit", "junit", "4.13.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("com.jayway.jsonpath:json-path:2.6.0")

    // test containers
    testImplementation("org.testcontainers:testcontainers:1.17.1")
    testImplementation("org.testcontainers:junit-jupiter:1.17.1")
    testImplementation("org.testcontainers:postgresql:1.16.0")
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }
tasks.kotest { enabled = true }
