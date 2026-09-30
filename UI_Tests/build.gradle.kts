plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.assertj:assertj-core:3.27.7")
    implementation("org.seleniumhq.selenium:selenium-java:4.47.0")
    implementation("com.codeborne:selenide:7.18.0")
    implementation("io.rest-assured:rest-assured:5.5.6")
    implementation("org.aeonbits.owner:owner:1.0.12")
    testImplementation(project(":Api-Tests"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("allUITests") {
    doLast {
        println("Test run is over")
    }
    group = "test"
    description = "Run all UI tests."
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
    useJUnitPlatform {
        includeTags("UI-test")
    }
}
