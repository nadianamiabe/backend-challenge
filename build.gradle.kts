import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.11"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("plugin.noarg") version "1.6.21"
}

//ext {
//	set("springCloudVersion", "2022.0.1")
//}

group = "br.com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}
//
//dependencyManagement {
//	imports {
//		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3") {
//			bomProperty("aws-java-sdk.version", "1.11.666")
//		}
//	}
//}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.3")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-test")
	implementation("com.auth0:java-jwt:4.2.1")
	implementation("org.jetbrains.kotlin:kotlin-allopen")
	implementation("org.jetbrains.kotlin:kotlin-noarg")
	implementation("org.springframework.cloud:spring-cloud-openfeign-core")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//	implementation("io.github.openfeign:feign-jackson:11.10")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.hamcrest:hamcrest:2.1")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.flywaydb:flyway-core")
	runtimeOnly("org.hsqldb:hsqldb")
	runtimeOnly("mysql:mysql-connector-java:5.1.40")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
