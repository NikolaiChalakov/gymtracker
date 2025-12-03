//plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
//    id("com.google.devtools.ksp")
//    id("kotlin-parcelize")
//}
//plugins {
//    alias(libs.plugins.android.application)
//    id("org.jetbrains.kotlin.android") // <--- Премахва alias, който може да носи грешна версия
//    id("com.google.devtools.ksp")
//    id("kotlin-parcelize")
//}
/*plugins {
    alias(libs.plugins.android.application)
    // Alias за Kotlin
    alias(libs.plugins.kotlin.android)

    // ПРИЛАГАНЕ НА KSP
    id("com.google.devtools.ksp")// БЕЗ apply false

    // Ако използвате Kotlin Parcelize:
    id("kotlin-parcelize")
}*/
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.devtools.ksp") version "2.0.21-1.0.25"
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.gymtracker"
    compileSdk = 36

    buildFeatures { viewBinding = true }
    defaultConfig {
        applicationId = "com.example.gymtracker"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

/*
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

  // Архитектурни Компоненти (MVVM, Coroutines, Navigation)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    //  Room Database
    val room_version = "2.6.1" // или 2.7.0+
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version") // Този ред е критичен
    // Retrofit (по-нова стабилна версия)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// OkHttp Logging Interceptor (по-нова стабилна версия)
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")
}*/
dependencies {
    // Връщаме всички зависимости към твърди, стабилни версии, за да избегнем конфликти с libs.
    // Core KTX & UI
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Архитектурни Компоненти (MVVM, Coroutines, Navigation)
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    val coroutines_version = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val nav_version = "2.7.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Room Database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version") // Добавяме ktx за coroutines поддръжка
    ksp("androidx.room:room-compiler:$room_version")

    // Retrofit (Стабилна версия)
    val retrofit_version = "2.9.0" // Използваме 2.9.0, защото 2.11.0 изисква по-нови JDK/Kotlin
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // OkHttp Logging Interceptor (Стабилна версия 4.x)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") // Махаме alpha версията

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}