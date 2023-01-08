dependencies {
    implementation(project(":common"))
    
    implementation(project(":user:infrastructure"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // mysql
    implementation("mysql:mysql-connector-java")
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }