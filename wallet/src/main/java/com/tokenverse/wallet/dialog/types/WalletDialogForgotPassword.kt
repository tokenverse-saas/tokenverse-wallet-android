package com.tokenverse.wallet.dialog.types

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.tokenverse.wallet.R
import com.tokenverse.wallet.dialog.WalletDialog
import com.tokenverse.wallet.uikit.HeaderView
import kotlinx.android.synthetic.main.dialog_forgot_password.view.*

class WalletDialogForgotPassword: WalletDialogInterface {

    override var builder: WalletDialog.Builder

    constructor(builder: WalletDialog.Builder) {
        this.builder = builder
    }

    override fun constroy(): View {
        val activity = builder.getActivity()
        val headerColor = builder.getHeaderColor()
        val isDarkMode = builder.getIsDarkMode()
        val onClickListener = builder.getOnClickListener()

        val layoutView =
            builder.getActivity().layoutInflater.inflate(R.layout.dialog_forgot_password, null)

        // GET COMPONENTS
        val mainComponent = layoutView.main
        val header: HeaderView = layoutView.headerView
        val textTitle: AppCompatTextView = layoutView.title
        val textSubtitle: AppCompatTextView = layoutView.subtitle

        // SET TEXTS
        textTitle.text = activity.getString(R.string.signup_title)
        textSubtitle.text = activity.getString(R.string.signup_subtitle)

        // textInfoSecurity.text = activity.getString(R.string.secured_by_tokenverse)

        header.setHeaderColor(headerColor)
        header.setButtonCloseOnClickListener(builder)

        return layoutView
    }
}