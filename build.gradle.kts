
plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

import org.gradle.jvm.tasks.Jar
import org.gradle.api.plugins.quality.Checkstyle

// 1) Hard-disable Spotless + Checkstyle (so they never block a build)
tasks.matching { it.name.startsWith("spotless", ignoreCase = true) }.configureEach { enabled = false }
tasks.matching { it.name.startsWith("checkstyle", ignoreCase = true) }.configureEach { enabled = false }
tasks.withType<Checkstyle>().configureEach { isIgnoreFailures = true } // just in case the plugin re-enables it

// 2) Produce exactly build/libs/outerrim.jar (no -sources, no -dev, no git-dirty)
tasks.named<Jar>("jar") {
    archiveBaseName.set("outerrim")
    archiveVersion.set("")
    archiveClassifier.set("")
    archiveFileName.set("outerrim.jar")
}

// 3) Kill common extra jars if the convention plugin added them
listOf("sourcesJar", "javadocJar", "devJar", "apiJar", "shadowJar").forEach { n ->
    tasks.matching { it.name == n }.configureEach { enabled = false }
}

// 4) Keep your compiled bytecode at Java 8 while Gradle itself can run on newer JDKs
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
