dependencies {
    // swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // logging
    implementation("io.github.microutils:kotlin-logging:1.12.5")
}

tasks.bootJar { enabled = false }
