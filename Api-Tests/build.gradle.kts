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
    implementation("io.rest-assured:rest-assured:5.5.6")
    implementation("org.projectlombok:lombok:1.18.46")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.1")
    implementation("org.aeonbits.owner:owner:1.0.12")
}

tasks.test {
    useJUnitPlatform()
}


tasks.register<Test>("allApiTests") {
    doLast {
        println("Test run is over")
    }
    group = "test"
    description = "Run all Api tests."
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
    useJUnitPlatform {
        includeTags("Api-test")
    }
}