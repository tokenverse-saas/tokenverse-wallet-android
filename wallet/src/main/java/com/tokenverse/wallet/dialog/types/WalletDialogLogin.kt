package com.tokenverse.wallet.dialog.types
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.R
import com.tokenverse.wallet.dialog.WalletDialog
import com.tokenverse.wallet.uikit.HeaderView
import kotlinx.android.synthetic.main.dialog_login.view.*


class WalletDialogLogin: WalletDialogInterface {
    override var builder: WalletDialog.Builder

    constructor(builder: WalletDialog.Builder) {
        this.builder = builder
    }

    override fun constroy(): View {
        val activity = builder.getActivity()
        val headerColor = builder.getHeaderColor()
        val isDarkMode = builder.getIsDarkMode()

        val layoutView: View = activity.layoutInflater.inflate(R.layout.dialog_login, null)

        // GET COMPONENTS
        val mainComponent = layoutView.main
        val header: HeaderView = layoutView.headerView
        val textTitle: AppCompatTextView = layoutView.title
        //val textSubtitle: AppCompatTextView = layoutView.signin_subtitle
        //val textSecured: AppCompatTextView = layoutView.signup_secured_info
        //val signUpButton: AppCompatButton = layoutView.btn_action_signup
        //val logInButton: Button = layoutView.btn_l


       /* val emailTextInput: TextInputLayout = layoutView.signup_email_address
        val passwordTexTInput: TextInputLayout = layoutView.signup_password
        val emailEditText: TextInputEditText = layoutView.signup_email_address_edit
        val passwordEditText: TextInputEditText = layoutView.signup_password_edit*/

        // SET TEXTS
        textTitle.text = activity.getString(R.string.login_title)
        /* textSubtitle.text = activity.getString(R.string.signup_subtitle)
         textSubtitle.text = activity.getString(R.string.signup_subtitle)
         textPasswordChars.text = activity.getString(R.string.signup_password_chars)
         textPasswordUppercase.text = activity.getString(R.string.signup_password_uppercase)
         textPasswordLowercase.text = activity.getString(R.string.signup_password_lowercase)
         textPasswordNumber.text = activity.getString(R.string.signup_password_number)

         signUpButton.text = activity.getString(R.string.signup_button)
         logInButton.text = activity.getString(R.string.signup_login_button)*/

        // SET COLORS


        header.setHeaderColor(headerColor)
        header.setDarkMode(isDarkMode)
        header.setButtonCloseOnClickListener(builder)

        // SET ICON
        if (isDarkMode) {
            val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_dark)
            mainComponent.setBackgroundColor(backgroundColor)
            val textColor =  ContextCompat.getColor(activity, R.color.white)

           /* textTitle.setTextColor(textColor)
            textSubtitle.setTextColor(textColor)
            textSecured.setTextColor(textColor)

            emailTextInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
            emailEditText.setTextColor(textColor)

            passwordTexTInput.setEndIconTintList(ColorStateList.valueOf(textColor))
            passwordEditText.setTextColor(textColor)
            passwordTexTInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))*/
        } else {
            val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_light)
            mainComponent.setBackgroundColor(backgroundColor)

            val textColor =  ContextCompat.getColor(activity, R.color.neutral_darker)

            //textTitle.setTextColor(textColor)
            //textSubtitle.setTextColor(textColor)
           // textSecured.setTextColor(textColor)
        }

        /*passwordEditText.addTextChangedListener(object : TextWatcher {
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

        })*/

        return layoutView
    }

}