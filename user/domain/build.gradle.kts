plugins {
    kotlin("plugin.jpa") version "1.6.21"
}

dependencies {
    implementation(project(":common"))

    implementation("javax.persistence:javax.persistence-api:2.2")
}