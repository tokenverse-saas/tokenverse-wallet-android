package com.tokenverse.wallet.dialog.types

import android.view.View
import com.tokenverse.wallet.dialog.WalletDialog.Builder

interface WalletDialogInterface {
    fun constroy(): View
    val builder: Builder
}