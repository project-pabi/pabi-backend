dependencies {
    implementation(project(":common"))

    implementation(project(":user:application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")
}

tasks.bootJar { enabled = false }