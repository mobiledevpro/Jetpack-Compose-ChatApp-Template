plugins {
    id("core-module")
}

android.defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

// It solve the issue with testing Coroutines Flow / ViewModel state
// and getting the error "app.cash.turbine.TurbineAssertionError: No value produced in 3s"
android.testOptions {
    unitTests.isReturnDefaultValues = true
}

dependencies {
    androidTestImplementation(platform(libs.library("compose.bom")))
    androidTestImplementation(libs.bundle("test.android"))
    androidTestImplementation(libs.bundle("test.koin"))

    testImplementation(libs.bundle("test.common"))
    testImplementation(libs.bundle("test.koin"))
}