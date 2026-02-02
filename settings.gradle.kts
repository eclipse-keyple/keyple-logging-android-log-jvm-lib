rootProject.name = "keyple-logging-android-log-jvm-lib"

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    mavenLocal()
    maven(url = "https://central.sonatype.com/repository/maven-snapshots")
  }
}
