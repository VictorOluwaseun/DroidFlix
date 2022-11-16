apply {
    from("$rootDir/base-module.gradle")
}
apply(plugin = "org.jetbrains.kotlin.android")
//apply(plugin = "org.jetbrains.kotlin.android")

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.movieListDomain))

    // Consider putting them in Core-UI
    "implementation"(AndroidX.coreKtx)
    "implementation"(AndroidX.appCompat)
    "implementation"(AndroidX.navigation)
    "implementation"(AndroidX.navigationFragment)

    "implementation"(Glide.glide)
    "kapt"(Glide.glideAnnotationProcessor)

//    "implementation"("androidx.legacy:legacy-support-v4:1.0.0")
//    "implementation"("androidx.recyclerview:recyclerview:1.2.1")
}

