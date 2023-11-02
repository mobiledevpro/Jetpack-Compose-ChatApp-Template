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

    api(projects.core.ui.dependencyProject)
    api(projects.core.di.dependencyProject)
    implementation(projects.core.domain.dependencyProject)

    implementation(projects.feature.home.dependencyProject)
    implementation(projects.feature.onboarding.dependencyProject)
    implementation(projects.feature.subscription.dependencyProject)
    implementation(projects.feature.chatList.dependencyProject)
    implementation(projects.feature.people.dependencyProject)
    implementation(projects.feature.userProfile.dependencyProject)
}