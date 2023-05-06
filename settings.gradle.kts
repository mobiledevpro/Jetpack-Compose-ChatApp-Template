pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "App Template Compose"

include(":app")
include(":core:ui")
include(":core:navigation")
include(":core:domain")

include(":feature:home")
include(":feature:onboarding")
include(":feature:subscription")
include(":feature:chat_list")
include(":feature:people_list")
include(":feature:user_profile")
include(":feature:people_profile")
include(":feature:people")
