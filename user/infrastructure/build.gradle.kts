dependencies {
    implementation(project(":user:presentation"))
    implementation(project(":user:application"))
    implementation(project(":user:domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}