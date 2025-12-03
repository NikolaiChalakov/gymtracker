pluginManagement {
    repositories {
        // ТУК Е КРИТИЧНАТА ЧАСТ: Google repository трябва да бъде тук,
        // за да може Gradle да намери плъгини като AGP, KSP и др.
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {

        id("androidx.navigation.safeargs.kotlin") version "2.7.5" // ⬅️ Добавете това
    }
}

dependencyResolutionManagement {
    // Уверете се, че използвате вашия namespace
    // rootProject.name = "GymTracker"
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Тези repositories се използват за всички зависимости (implementation, api, ksp и т.н.)
        google()
        mavenCentral()
    }
}

rootProject.name = "GymTracker"
include(":app")