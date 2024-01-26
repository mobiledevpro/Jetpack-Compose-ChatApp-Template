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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    flavorDimensions += listOf("default")

    productFlavors {
        create("production") {
            dimension = "default"
        }

        create("dev") {
            dimension = "default"
        }
    }

    println("# Namespace: $projectName")
    println("# Compile SDK version: ${libs.versionStr("sdk.compile")}")
    println("# Min SDK version: ${libs.versionStr("sdk.min")}")
    println("# Compose Compiler version: ${libs.versionStr("compose.compiler")}")
}