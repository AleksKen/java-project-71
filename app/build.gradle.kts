plugins {
    id ("application")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}