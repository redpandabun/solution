val javaVersion = libs.versions.java.get().toInt()

group = properties["my-solution.group"]!!
version = properties["my-solution.version"]!!

repositories {
  mavenCentral()
}

plugins {
  alias(libs.plugins.kotlin)
  alias(libs.plugins.kotlin.jpa)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.kotlinx.kover)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependencyManagement)
}

dependencies {
  implementation(libs.bundles.api)
  runtimeOnly(libs.bundles.runtime)
  developmentOnly(libs.bundles.dev)
  testImplementation(libs.bundles.test)
}

tasks {
  test {
    useJUnitPlatform()
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(javaVersion)
  }
}

kotlin {
  jvmToolchain(javaVersion)

  compilerOptions {
    freeCompilerArgs = "${properties["kotlin.compiler.args"]}".split(" ")
  }
}

// kotlin-jpa
noArg {}

// kotlin-spring
allOpen {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

kover {
  reports {
    filters {
      excludes {
        annotatedBy("jakarta.persistence.Entity")
        annotatedBy("javax.persistence.Embeddable")
        annotatedBy("javax.persistence.MappedSuperclass")

        annotatedBy("org.springframework.context.annotation.Configuration")
        annotatedBy("org.springframework.boot.autoconfigure.SpringBootApplication")

        inheritedFrom("org.springframework.data.repository.Repository")
      }
    }

    verify {
      rule {
        minBound(80)
      }
    }
  }
}
