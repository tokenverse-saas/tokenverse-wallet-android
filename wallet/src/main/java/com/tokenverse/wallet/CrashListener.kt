package com.tokenverse.wallet

import androidx.annotation.Keep

@Keep
interface CrashListener {
    fun onUncaughtException(thread: Thread, throwable: Throwable)
}

@Keep
object Wallet {
    @JvmStatic
    fun init(crashListener: CrashListener) {
        Thread.setDefaultUncaughtExceptionHandler { p0, p1 ->
            crashListener.onUncaughtException(p0, p1)
        }
    }
}