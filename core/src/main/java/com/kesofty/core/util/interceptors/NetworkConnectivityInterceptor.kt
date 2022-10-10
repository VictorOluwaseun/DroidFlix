package com.kesofty.core.util.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.kesofty.core.util.exceptions.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectivityInterceptor(private val context: Context) : Interceptor {
    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (isNetworkAvailable) {
            chain.proceed(chain.request())
        } else {
            throw NoNetworkException()
        }
    }
}