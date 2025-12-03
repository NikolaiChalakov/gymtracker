/*
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    // КЪМ: (Нова стабилна версия, съвместима с по-нов Kotlin)
    id("com.google.devtools.ksp") version "2.0.0-1.0.21" apply false
}*/
// Top-level build.gradle.kts (Project Level)

//plugins {
//    alias(libs.plugins.android.application) apply false
//    // ФИКСИРАЙТЕ TУК: Преминете към Kotlin 1.9.23
//    alias(libs.plugins.kotlin.android) version "1.9.23" apply false
//    // ФИКСИРАЙТЕ TУК: KSP, съвместим с 1.9.23
//    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
//}
//plugins {
//    // Ако "libs.plugins.android.application" сочи към по-нов AGP, може да има проблем,
//    // но ще приемем, че е съвместим с Kotlin 1.9.23.
//
//    // ФИКСИРАНЕ НА KOTLIN PLUGINS на 1.9.23
//    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
//
//    // ФИКСИРАНЕ НА KSP PLUGINS на 1.9.23-1.0.20
//    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
//}
/*
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false // Уверете се, че сте добавили този alias
}*/
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

