group = rootProject.group
version = rootProject.version

dependencies {
    implementation(project(":kotlin-analysis-core"))
    testImplementation(project(":kotlin-analysis-core", "tests"))
}
