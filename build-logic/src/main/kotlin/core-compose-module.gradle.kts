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
    //Compose
    api(platform(libs.library("compose.bom")))
    api(libs.bundle("compose"))
    debugApi(libs.bundle("compose.debug"))
}