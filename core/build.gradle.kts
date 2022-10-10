import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

apply {
    from("$rootDir/base-module.gradle")
}

//val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY")



dependencies {

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.gsonConverter)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)

}