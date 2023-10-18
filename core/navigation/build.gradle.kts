@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   id("core-compose-module")
}


dependencies {
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)

    api(libs.navigation)

    implementation(libs.bundles.koin)

    implementation(project(":core:ui"))
    implementation(project(":core:domain"))

    implementation(project(":feature:home"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:subscription"))
    implementation(project(":feature:chat_list"))
    implementation(project(":feature:people"))
    implementation(project(":feature:user_profile"))
}