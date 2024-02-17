plugins {
    id("core-module")
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versionStr("compose.compiler")
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:di"))
    implementation(project(":core:domain"))
    implementation(project(":core:coroutines"))

    implementation(libs.bundle("lifecycle"))
    implementation(libs.library("coil"))
    implementation(libs.library("activity.ktx"))
}