plugins {
    id("feature-module")
}

dependencies {
    api(projects.feature.peopleList.dependencyProject)
    api(projects.feature.peopleProfile.dependencyProject)
}