dependencies {
    implementation(project(":user:infrastructure"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }