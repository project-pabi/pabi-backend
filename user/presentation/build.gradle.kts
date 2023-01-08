dependencies {
    implementation(project(":common"))

    implementation(project(":user:application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.bootJar { enabled = false }