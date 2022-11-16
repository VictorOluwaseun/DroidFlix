apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.movieListDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.gsonConverter)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}