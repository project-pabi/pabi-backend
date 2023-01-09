dependencies {
    implementation(project(path = ":common", configuration = "default"))

    implementation(project(":user:application"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.bootJar { enabled = false }