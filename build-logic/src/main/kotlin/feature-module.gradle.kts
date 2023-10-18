plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-convention")
}

val projectName = project.name.replace("_", ".")

android {
    namespace = "com.mobiledevpro.$projectName"
    compileSdk = libs.versionInt("sdk.compile")

    defaultConfig {
        minSdk = libs.versionInt("sdk.min")
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versionStr("compose.compiler")
    }

    // It solve the issue with testing Coroutines Flow / ViewModel state
    // and getting the error "app.cash.turbine.TurbineAssertionError: No value produced in 3s"
    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    println("# Namespace: $projectName")
    println("# Compile SDK version: ${libs.versionStr("sdk.compile")}")
    println("# Min SDK version: ${libs.versionStr("sdk.min")}")
    println("# Compose Compiler version: ${libs.versionStr("compose.compiler")}")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
}