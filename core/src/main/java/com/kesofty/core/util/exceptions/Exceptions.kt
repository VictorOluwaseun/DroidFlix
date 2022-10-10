package com.kesofty.core.util.exceptions

import java.io.IOException

open class NetworkException(val code: Int, message: String) : IOException(message)
class NoNetworkException : IOException("No internet connection available")
class ServerException(code: Int, message: String) : NetworkException(code, message)