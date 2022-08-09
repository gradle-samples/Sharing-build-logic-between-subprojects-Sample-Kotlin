// Define Java Library conventions for this organization.
// Projects need to use the organization's Java conventions and publish using Maven Publish

plugins {
    `java-library`
    `maven-publish`
    id("myproject.java-conventions")
}

// Projects have the 'com.example' group by convention
group = "com.example"

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "myOrgPrivateRepo"
            url = uri("build/my-repo")
        }
    }
}

// The project requires libraries to have a README containing sections configured below
val readmeCheck by tasks.registering(com.example.ReadmeVerificationTask::class) {
    readme.set(layout.projectDirectory.file("README.md"))
    readmePatterns.set(listOf("^## API$", "^## Changelog$"))
}

tasks.named("check") { dependsOn(readmeCheck) }
