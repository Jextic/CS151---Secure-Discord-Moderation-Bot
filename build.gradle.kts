import org.gradle.jvm.tasks.Jar

plugins {
    idea
    application
    id("java")
}

group = "edu.sjsu.cs"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Taken from https://mvnrepository.com/artifact/net.dv8tion/JDA
    implementation("net.dv8tion:JDA:5.0.0-beta.23")
    // Taken from https://mvnrepository.com/artifact/commons-cli/commons-cli
    implementation("commons-cli:commons-cli:1.7.0")
    // Taken from https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.1.0-alpha1")
    // Taken from https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.1.0-alpha1")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("edu.sjsu.cs.Main")
}

val fatJar = task("fatJar", type = Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "ModeratorBot"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "edu.sjsu.cs.Main"
    }

    from(configurations.runtimeClasspath.get().map{
        if (it.isDirectory) it else zipTree(it)
    })

    with(tasks.jar.get() as CopySpec)
    destinationDirectory.set(layout.buildDirectory.dir("dist"))
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}

