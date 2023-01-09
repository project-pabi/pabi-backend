dependencies {
    implementation(project(path = ":common", configuration = "default"))
    
    implementation(project(":user:infrastructure"))

    // mysql
    implementation("mysql:mysql-connector-java")
}

tasks.jar { enabled = false }
tasks.bootJar { enabled = true }