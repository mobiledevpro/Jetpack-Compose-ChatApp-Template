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
include(":feature:home")
include(":feature:onboarding")
include(":feature:subscription")
include(":feature:chat_list")
