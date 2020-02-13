import org.hidetake.gradle.swagger.generator.GenerateSwaggerCode
import org.hidetake.gradle.swagger.generator.GenerateSwaggerUI

plugins {
    id("org.hidetake.swagger.generator") version "2.18.1"
    kotlin("plugin.spring")
}


dependencies {
    implementation( project(":core", "default"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    swaggerCodegen("org.openapitools:openapi-generator-cli:4.2.3")
    swaggerUI ("org.webjars:swagger-ui:3.25.0")
    implementation( "org.mapstruct:mapstruct:${Versions.mapstruct}" )
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


swaggerSources {
    register("springBootKotlin") {
        setInputFile(file("./openapi.yaml"))
        code(delegateClosureOf<GenerateSwaggerCode> {
            language = "kotlin-spring"
            components = listOf("models", "apis")
            configFile = file("./api-generator-config.json")
            outputDir = file("${project.buildDir}/generated/openapi")
        })
        ui(delegateClosureOf<GenerateSwaggerUI> {
            outputDir = file("${project.buildDir}/generated/openapi/src/main/resources/static")
        })
    }
}

tasks.named("compileKotlin").configure {
    dependsOn(tasks.named("generateSwaggerCode"))
}
tasks.named("processResources").configure {
    dependsOn(tasks.named("generateSwaggerUI"))
}


sourceSets {
    val main by getting
    val springBootKotlin by swaggerSources.getting
    main.java.srcDir("${springBootKotlin.code.outputDir}/src/main/kotlin")
    main.resources.srcDir("${project.buildDir}/generated/openapi/src/main/resources")
}
