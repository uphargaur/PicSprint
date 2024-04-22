plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.uphar.picsprint"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uphar.picsprint"
        minSdk = 24
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core-jvm:1.1.0-beta02")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.firebase:firebase-database-ktx:20.3.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.0")

    val nav_version = "2.7.5"

    implementation("androidx.compose.foundation:foundation:1.5.4")

    implementation("androidx.compose.material:material-icons-core:1.0.5")
    implementation("androidx.compose.material:material-icons-extended:1.0.5")

    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-gif:2.1.0")

    // Jetpack Compose
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.compose.material:material:1.0.1")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation( "androidx.compose.ui:ui:1.6.2")
    implementation( "androidx.compose.material:material:1.6.2")
    implementation("androidx.compose.ui:ui-tooling:1.6.2")
    implementation( "androidx.compose.foundation:foundation:1.6.2")
    implementation( "androidx.compose.runtime:runtime-livedata:1.6.2")
    implementation( "androidx.activity:activity-compose:1.8.2")
    implementation( "androidx.compose.runtime:runtime:1.6.2" )
    implementation( "androidx.compose.material:material:1.6.2")
    implementation( "androidx.compose.material:material-icons-core:1.6.2")
    implementation( "androidx.compose.material:material-icons-extended:1.6.2")


    //webrtc
    implementation ("org.java-websocket:Java-WebSocket:1.5.3")
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation ("com.mesibo.api:webrtc:1.0.5")


    //dagger
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")



    // DataStore
    var dataStoreVersion = "1.0.0"
    implementation ("androidx.datastore:datastore-preferences:$dataStoreVersion")

    // Retrofit
    var retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")
}