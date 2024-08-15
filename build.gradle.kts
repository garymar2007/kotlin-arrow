plugins {
    kotlin("jvm") version "2.0.0"
    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
}

group = "org.gary"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.arrow-kt:arrow-stack:1.2.4"))
    // no versions on libraries
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-fx-coroutines")

    implementation("io.arrow-kt:arrow-optics:1.2.4")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.4")

    testImplementation(kotlin("test"))
    implementation("io.kotest:kotest-framework-engine-jvm:5.5.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}