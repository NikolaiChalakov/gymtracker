pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {

        id("androidx.navigation.safeargs.kotlin") version "2.7.5"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {

        google()
        mavenCentral()
    }
}

rootProject.name = "GymTracker"
include(":app")