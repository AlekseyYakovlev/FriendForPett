plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        buildToolsVersion = "30.0.3"

        applicationId = "ru.friendforpet"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions.annotationProcessorOptions.argument("room.incremental", "true")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true

        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("serverType")
    productFlavors {
        create("serverType") {
            buildConfigField("String", "BASE_URL", "\"https://documents.zenithapps.net/api/\"")
            buildConfigField("String", "API_KEY", "\"some_key\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xinline-classes") // allows to use inline-classes
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.5.0-beta03")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.2.0-beta02")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    // Activity KTX
    implementation("androidx.activity:activity-ktx:1.3.0-alpha04")

    // Fragment KTX
    implementation("androidx.fragment:fragment-ktx:1.3.1")

    // Lifecycle, ViewModel and LiveData
    val lifecycleVersion = "2.3.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // Coroutines
    val coroutinesVersion = "1.4.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // Coil
    implementation("io.coil-kt:coil:1.1.1")

    // Retrofit + OkHttp
    val okHttpVersion = "5.0.0-alpha.2"
    val retofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    // Moshi
    val moshiVersion = "1.11.0"
    implementation ("com.squareup.retrofit2:converter-moshi:$retofitVersion")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    // Dagger + Hilt
    val daggerVersion = "2.33"
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hiltVersion"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hiltVersion"]}")
    val androidxHilt = "1.0.0-beta01"
    kapt("androidx.hilt:hilt-compiler:$androidxHilt")

    //Room
    val roomVersion = "2.3.0-beta03"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    //Log
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}