dependencies {
    implementation(project(path = ":common", configuration = "default"))
    
    implementation(project(":user:infrastructure"))
    implementation(project(":user:domain"))

    // mysql
    implementation("mysql:mysql-connector-java")
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }