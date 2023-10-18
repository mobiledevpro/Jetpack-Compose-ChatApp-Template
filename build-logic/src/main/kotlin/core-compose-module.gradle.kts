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
    //Compose
    api(platform(libs.library("compose.bom")))
    api(libs.bundle("compose"))
    debugApi(libs.bundle("compose.debug"))

    androidTestApi(platform(libs.library("compose.bom")))
    androidTestApi(libs.bundle("test.android"))
}