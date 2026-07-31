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
    testImplementation("org.assertj:assertj-core:3.27.7")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("task1.1"){
    group = "Test"
    description = "Run all tests"
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
    useJUnitPlatform()
    finalizedBy("task1.2")
}

tasks.register<Test>("task1.2"){
    group = "Test"
    doLast{
        println("Test run is over")
    }


}

tasks.register<Test>("lesson3") {
    group = "Test"
    description = "Run test lesson3"
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
    useJUnitPlatform {
        includeTags ("Lesson3")
    }
}
