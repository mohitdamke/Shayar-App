plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


android {

    namespace = "com.example.shayariapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shayariapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    }



    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.navigation.common.ktx)
        implementation(libs.androidx.navigation.runtime.ktx)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.androidx.datastore.core.android)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.runtime.livedata)
        implementation(libs.androidx.work.runtime.ktx)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

        implementation("com.squareup.retrofit2:retrofit:2.7.2")
        implementation("com.squareup.retrofit2:converter-gson:2.7.2")
        implementation("com.squareup.okhttp3:okhttp:3.6.0")



        implementation("androidx.compose.material:material-icons-extended:1.7.0-rc01")
        implementation("androidx.room:room-ktx:2.6.1")
        kapt("androidx.room:room-compiler:2.6.1")
        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-paging:2.6.1")

//      Paging
        implementation("androidx.paging:paging-runtime:3.1.1")
        implementation("androidx.paging:paging-compose:3.3.2")

        // Notification
//        implementation("androidx.core:core-ktx:2.2.0")


        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

        // Coroutine Lifecycle Scopes
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")

        implementation("androidx.datastore:datastore-preferences-core:1.1.1")

        // System ui controller
        implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
        // Dagger Hilt
        implementation("com.google.dagger:hilt-android:2.51.1")
        kapt("com.google.dagger:hilt-compiler:2.51.1")
        kapt("androidx.hilt:hilt-compiler:1.2.0")
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
        implementation("androidx.navigation:navigation-compose:2.7.7")

        implementation("androidx.compose.material3:material3:1.2.0-rc01")

// Glance

        implementation("androidx.glance:glance-appwidget:1.1.0")
        implementation("androidx.glance:glance-material3:1.1.0")

}