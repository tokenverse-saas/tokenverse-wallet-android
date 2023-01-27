package com.tokenverse.wallet.dialog.types

import android.view.View
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.R
import com.tokenverse.wallet.dialog.WalletDialog
import com.tokenverse.wallet.uikit.CheckBoxTextView
import com.tokenverse.wallet.uikit.HeaderView
import com.tokenverse.wallet.uikit.IconTextListItemView
import com.tokenverse.wallet.uikit.SecuredInfoView
import kotlinx.android.synthetic.main.dialog_signin_info.view.*

class WalletDialogInfo: WalletDialogInterface {
    override var builder: WalletDialog.Builder

    constructor(builder: WalletDialog.Builder) {
        this.builder = builder
    }

    override  fun constroy(): View {
        val activity = builder.getActivity()
        val headerColor = builder.getHeaderColor()
        val isDarkMode = builder.getIsDarkMode()

        val layoutView = activity.layoutInflater.inflate(R.layout.dialog_signin_info, null)

        // GET COMPONENTS
        val mainComponent = layoutView.main_signin_info
        val header: HeaderView = layoutView.headerView
        val textTitle: AppCompatTextView = layoutView.signin_info_title
        val itemSecurity: IconTextListItemView = layoutView.item_security
        val itemSafeGuard: IconTextListItemView = layoutView.item_safeguard
        val textInfoPolicies: AppCompatTextView = layoutView.signin_info_policies
        val checkBoxDontShow: CheckBoxTextView = layoutView.cbDontShow
        val securedInfo: SecuredInfoView = layoutView.securedInfo

        val continueButton: AppCompatButton = layoutView.btn_action_continue

        // SET TEXTS
        textTitle.text = activity.getString(R.string.signin_info_title)

        itemSecurity.setDarkMode(isDarkMode)
        itemSafeGuard.setDarkMode(isDarkMode)

        textInfoPolicies.text = activity.getString(R.string.signin_info_policies)
        continueButton.text = activity.getString(R.string.signin_info_continue_button)

        // SET COLORS
        header.setHeaderColor(headerColor)
        header.setDarkMode(isDarkMode)
        header.setButtonCloseOnClickListener(builder)

        checkBoxDontShow.setDarkMode(isDarkMode)
        securedInfo.setDarkMode(isDarkMode)

        // SET ICON
        if (isDarkMode) {
            val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_dark)
            mainComponent.setBackgroundColor(backgroundColor)
            val textColor =  ContextCompat.getColor(activity, R.color.white)

            textTitle.setTextColor(textColor)
            textInfoPolicies.setTextColor(textColor)
            //checkBox.setTextColor(textColor)
        } else {
            val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_light)
            mainComponent.setBackgroundColor(backgroundColor)

            val textColor =  ContextCompat.getColor(activity, R.color.neutral_darker)

            textTitle.setTextColor(textColor)
            textInfoPolicies.setTextColor(textColor)
            //checkBox.setTextColor(textColor)
        }

        /*continueButton.setOnClickListener {
            builder.getOnClickListener().onClick(builder)
        }*/

        return layoutView
    }
}