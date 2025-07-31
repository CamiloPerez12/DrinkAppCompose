pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "DrinkApp"
include(":app")
include(":features")
include(":network")
include(":data")
include(":feature:homeScreen")
include(":designSystem")
include(":feature:loginScreen")
include(":feature:registerScreen")
include(":common:resources:drawable")
