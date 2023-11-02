plugins {
    id("core-module")
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versionStr("compose.compiler")
    }

    // It solve the issue with testing Coroutines Flow / ViewModel state
    // and getting the error "app.cash.turbine.TurbineAssertionError: No value produced in 3s"
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:di"))
    implementation(project(":core:domain"))
}