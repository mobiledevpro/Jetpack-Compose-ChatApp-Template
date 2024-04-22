import com.android.build.gradle.AppExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.mobiledevpro.apptemplate.compose"
    compileSdk = libs.versions.sdk.compile.get().toInt()


    defaultConfig {
        applicationId = "com.mobiledevpro"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        versionCode = libs.versions.app.version.code.get().toInt()
        versionName = libs.versions.app.version.name.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions += listOf("default")

    productFlavors {
        create("production") {
            dimension = "default"
            applicationIdSuffix = ".closetalk.app"
        }

        create("dev") {
            dimension = "default"
            applicationIdSuffix = ".apptemplate.compose"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    coreLibraryDesugaring(libs.desugaring)
    implementation(libs.bundles.lifecycle)

    implementation(projects.core.navigation)

    testApi(libs.bundles.test.common)
}


//This task is used for .AAB file renaming to following format:
//{application_id}-v{version_name}-build{version_code}-{product_flavor}-{build_type}.aab

tasks.register("renameBundle") {
    doLast {
        val androidExtension = project.extensions.findByType(AppExtension::class.java)

        androidExtension?.also { android ->

            println("Variant variants: ${android.applicationVariants}")

            android.applicationVariants.forEach { variant ->
                println("===============================")
                println("Variant name: ${variant.name}")
                println("Version name: ${variant.versionName}")
                println("Version code: ${variant.versionCode}")
                println("Flavor name: ${variant.productFlavors.map { it.name }.joinToString()}")


                val flavorName = variant.productFlavors.map { it.name }.joinToString()
                val variantName = variant.name
                val buildType = variant.buildType.name
                val versionName = variant.versionName
                val versionCode = variant.versionCode
                val bundleDir =
                    "$buildDir/outputs/bundle/${variantName}"

                val oldFileName = "app-${flavorName}-${buildType}.aab"
                val newFileName =
                    "${variant.applicationId}-" +
                            "v$versionName-" +
                            "build$versionCode-" +
                            "$flavorName-" +
                            "$buildType.aab"

                //remove an old file if exist
                delete("$bundleDir/$newFileName")

                val originalFile = file("$bundleDir/$oldFileName")

                if (!originalFile.exists()) {
                    println("Original '${originalFile.absolutePath}' file not found")
                } else {
                    println("newFileName: $newFileName")
                    val newFile = file("$bundleDir/$newFileName")
                    originalFile.renameTo(newFile)
                    println("Renamed '$oldFileName' file to $newFileName")
                }
            }

        }
            ?: println("Android extension not found. This task is only valid for Android application projects.")
    }
}

tasks.matching { task -> task.name.contains("release", ignoreCase = true) }
    .configureEach {
        finalizedBy("renameBundle")
    }