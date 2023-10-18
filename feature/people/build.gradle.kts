plugins {
    id("feature-module")
}

dependencies {
    api(project(":feature:people_list"))
    api(project(":feature:people_profile"))
}