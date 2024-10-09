plugins {
    id("core-module")
}

with(pluginManager) {
    apply("org.jetbrains.kotlin.plugin.compose")
}

android {
    buildFeatures.compose = true
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:di"))
    implementation(project(":core:domain"))
    implementation(project(":core:coroutines"))
    implementation(project(":core:util"))

    implementation(libs.bundle("lifecycle"))
    implementation(libs.library("coil"))
    implementation(libs.library("activity.ktx"))

    //Crashlytics
    implementation(project(":core:analytics"))
}