plugins {
    id("feature-module")
}

dependencies {
    testImplementation(libs.bundles.test.common)
    androidTestImplementation(libs.bundles.test.android)
}