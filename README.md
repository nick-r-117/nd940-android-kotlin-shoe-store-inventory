# Shoe Store Inventory App

Shoe Store Inventory App that has basic screens Login, Onboarding, Shoe Listings, and Shoe Details
Page. This app is part of Udacity Android Nanodegree course work to showcase core fundamentals such
as: Layouts, Navigation, Fragments, MVVC, Live Data, etc.

## Getting Started

Instructions for how to get a copy of the project running on your local machine.

1. Go to `https://github.com/nick-r-117/nd940-android-kotlin-shoe-store-inventory`
2. Copy the https url from clone button
3. past the url in terminal on local machine
```
git clone <copied_url>
```

### Dependencies
build.gradle(project)
```
    id 'com.android.application' version '8.4.1' apply false // Apply AGP here
    id 'org.jetbrains.kotlin.android' version '1.9.23' apply false // Apply Kotlin plugin here
    id 'androidx.navigation.safeargs.kotlin' version '2.7.7' apply false
```
build.gradle(app)
```
    // Kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0"

    // Android X
    implementation 'androidx.core:core-ktx:1.13.1'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation "com.jakewharton.timber:timber:5.0.1"

    // Navigation
    def nav_version = "2.7.7"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
```

### Installation

Step by step explanation of how to get a dev environment running.

1. Download Android Studio
2. Follow `Getting Started` section above to clone project
3. Will need to reconfig depending on your config setup

- Gradle version `gradle-8.14.3` (see gradle-wrapper.properties)
- Java 17
- minSDK 21
- TargetSDK 34

build.gradle(app)
```
compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
```

## Project Instructions

This section should contain all the student deliverables for this project.

Refer to https://github.com/nick-r-117/nd940-android-kotlin-shoe-store-inventory/blob/master/starter/README.md

## Built With

Include all items used to build project.

- Used Gemini plugin to assist in finding issues and recommend refactors
- Used Google Search & Google Gemini to find code examples and api docs for certain functionality i.e. LayoutParams
- Used previous Udacity Android course material to structure code

## License
