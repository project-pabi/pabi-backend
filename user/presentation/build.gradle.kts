dependencies {
    implementation(project(":common"))

    implementation(project(":user:application"))

    // swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.bootJar { enabled = false }