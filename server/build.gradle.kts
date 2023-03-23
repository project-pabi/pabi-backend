plugins {
    kotlin("plugin.jpa") version "1.6.21"
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
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }