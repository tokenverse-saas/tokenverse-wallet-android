package com.tokenverse.wallet.dialog.types

import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tokenverse.wallet.R
import com.tokenverse.wallet.dialog.WalletDialog
import com.tokenverse.wallet.uikit.HeaderView
import kotlinx.android.synthetic.main.dialog_signup.view.*
import java.util.regex.Pattern

class WalletDialogSignupEmail: WalletDialogInterface {
    override var builder: WalletDialog.Builder

    constructor(builder: WalletDialog.Builder) {
        this.builder = builder
    }

    override fun constroy(): View {
        val activity = builder.getActivity()
        val headerColor = builder.getHeaderColor()
        val isDarkMode = builder.getIsDarkMode()

        val layoutView: View = activity.layoutInflater.inflate(R.layout.dialog_signup, null)

        // GET COMPONENTS
        val mainComponent = layoutView.main_signin
        val header: HeaderView = layoutView.headerView
        val textTitle: AppCompatTextView = layoutView.signin_title
        val textSubtitle: AppCompatTextView = layoutView.signin_subtitle
        val icon10Chars: AppCompatImageView = layoutView.icon_10_chars
        val iconUpperCase: AppCompatImageView = layoutView.icon_uppercase
        val iconLowerCase: AppCompatImageView = layoutView.icon_lowercase
        val iconNumber: AppCompatImageView = layoutView.icon_number
        val signUpButton: AppCompatButton = layoutView.btn_action_signup
        val logInButton: Button = layoutView.btn_action_login
        val textPasswordChars: AppCompatTextView = layoutView.signup_password_chars
        val textPasswordUppercase: AppCompatTextView = layoutView.signup_password_uppercase
        val textPasswordLowercase: AppCompatTextView = layoutView.signup_password_lowercase
        val textPasswordNumber: AppCompatTextView = layoutView.signup_password_number
        val emailTextInput: TextInputLayout = layoutView.signup_email_address
        val passwordTexTInput: TextInputLayout = layoutView.signup_password
        val emailEditText: TextInputEditText = layoutView.signup_email_address_edit
        val passwordEditText: TextInputEditText = layoutView.signup_password_edit

        // SET TEXTS
        textTitle.text = activity.getString(R.string.signup_title)
        textSubtitle.text = activity.getString(R.string.signup_subtitle)
        textSubtitle.text = activity.getString(R.string.signup_subtitle)
        textPasswordChars.text = activity.getString(R.string.signup_password_chars)
        textPasswordUppercase.text = activity.getString(R.string.signup_password_uppercase)
        textPasswordLowercase.text = activity.getString(R.string.signup_password_lowercase)
        textPasswordNumber.text = activity.getString(R.string.signup_password_number)

        signUpButton.text = activity.getString(R.string.signup_button)
        logInButton.text = activity.getString(R.string.signup_login_button)

        // SET COLORS
        val grey500 = ContextCompat.getColor(activity, R.color.grey500)

        icon10Chars.setColorFilter(grey500, android.graphics.PorterDuff.Mode.SRC_IN)
        iconUpperCase.setColorFilter(grey500, android.graphics.PorterDuff.Mode.SRC_IN)
        iconLowerCase.setColorFilter(grey500, android.graphics.PorterDuff.Mode.SRC_IN)
        iconNumber.setColorFilter(grey500, android.graphics.PorterDuff.Mode.SRC_IN)

        textPasswordChars.setTextColor(grey500)
        textPasswordNumber.setTextColor(grey500)
        textPasswordLowercase.setTextColor(grey500)
        textPasswordUppercase.setTextColor(grey500)

        header.setHeaderColor(headerColor)
        header.setDarkMode(isDarkMode)
        header.setButtonCloseOnClickListener(builder)

        // SET ICON
        if (isDarkMode) {
            val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_dark)
            mainComponent.setBackgroundColor(backgroundColor)

            val textColor =  ContextCompat.getColor(activity, R.color.white)

            textTitle.setTextColor(textColor)
            textSubtitle.setTextColor(textColor)

            emailTextInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
            emailEditText.setTextColor(textColor)

            passwordTexTInput.setEndIconTintList(ColorStateList.valueOf(textColor))
            passwordEditText.setTextColor(textColor)
            passwordTexTInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
        } else {
            val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_light)
            mainComponent.setBackgroundColor(backgroundColor)

            val textColor =  ContextCompat.getColor(activity, R.color.neutral_darker)

            textTitle.setTextColor(textColor)
            textSubtitle.setTextColor(textColor)
        }

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val validColor = ContextCompat.getColor(activity, R.color.green700)
                val invalidColor = ContextCompat.getColor(activity, R.color.red700)

                if (s?.length ?: 0  < 10) {
                    icon10Chars.setColorFilter(invalidColor, android.graphics.PorterDuff.Mode.SRC_IN)
                } else {
                    icon10Chars.setColorFilter(validColor, android.graphics.PorterDuff.Mode.SRC_IN)
                }

                if(!s.toString().matches(".*[A-Z].*".toRegex())) {
                    iconUpperCase.setColorFilter(invalidColor, android.graphics.PorterDuff.Mode.SRC_IN)
                } else {
                    iconUpperCase.setColorFilter(validColor, android.graphics.PorterDuff.Mode.SRC_IN)
                }

                if(!s.toString().matches(".*[A-Z].*".toRegex())) {
                    iconUpperCase.setColorFilter(invalidColor, android.graphics.PorterDuff.Mode.SRC_IN)
                } else {
                    iconUpperCase.setColorFilter(validColor, android.graphics.PorterDuff.Mode.SRC_IN)
                }

                if(!s.toString().matches(".*[a-z].*".toRegex())) {
                    iconLowerCase.setColorFilter(invalidColor, android.graphics.PorterDuff.Mode.SRC_IN)
                } else {
                    iconLowerCase.setColorFilter(validColor, android.graphics.PorterDuff.Mode.SRC_IN)
                }

                if(!s.toString().matches(".*[0-9].*".toRegex())) {
                    iconNumber.setColorFilter(invalidColor, android.graphics.PorterDuff.Mode.SRC_IN)
                } else {
                    iconNumber.setColorFilter(validColor, android.graphics.PorterDuff.Mode.SRC_IN)
                }
            }

        })
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val validColor = ContextCompat.getColor(activity, R.color.green700)
                val invalidColor = ContextCompat.getColor(activity, R.color.red700)

                val EMAIL_ADDRESS_PATTERN = Pattern.compile(
                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                            "\\@" +
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                            "(" +
                            "\\." +
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                            ")+"
                )

                if (!EMAIL_ADDRESS_PATTERN.matcher(s).matches()) {
                    emailTextInput.error = activity.getString(R.string.signup_email_invalid)
                    emailTextInput.setErrorTextColor((ContextCompat.getColorStateList(activity, R.color.red700)))
                } else {
                    emailTextInput.error = ""
                }
            }
        })


        return layoutView
    }
}