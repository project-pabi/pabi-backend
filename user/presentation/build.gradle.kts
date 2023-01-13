dependencies {
    implementation(project(path = ":common", configuration = "default"))

    implementation(project(":user:application"))
    implementation(project(":user:domain"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.bootJar { enabled = false }