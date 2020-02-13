plugins {
    id("org.springframework.boot")
    id("org.hidetake.swagger.generator") version "2.18.1"
    kotlin("plugin.spring")
}


dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

//    implementation( project(":mongo"))
    implementation( project(":core", "default"))
    implementation( project(":rest", "default"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

