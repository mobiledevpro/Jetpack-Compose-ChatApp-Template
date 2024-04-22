plugins {
    id("core-module")
}

dependencies {
    implementation(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.crashlytics)
    api(libs.firebase.performance)
}