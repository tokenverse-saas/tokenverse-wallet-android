package com.tokenverse.wallet.dialog

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tokenverse.wallet.R
import kotlinx.android.synthetic.main.dialog_forgot_password.view.*
import kotlinx.android.synthetic.main.dialog_signin_info.view.*
import kotlinx.android.synthetic.main.dialog_signin_info.view.btn_action_continue
import kotlinx.android.synthetic.main.dialog_signin_info.view.image_close_signin_info
import kotlinx.android.synthetic.main.dialog_signin_info.view.img_icon_logo
import kotlinx.android.synthetic.main.dialog_signin_info.view.layout_header_signin_info
import kotlinx.android.synthetic.main.dialog_signin_info.view.signin_info_login
import kotlinx.android.synthetic.main.dialog_signin_info.view.signin_info_title
import kotlinx.android.synthetic.main.dialog_signup.view.*
import kotlinx.android.synthetic.main.dialog_signup.view.signup_email_address
import kotlinx.android.synthetic.main.dialog_signup.view.signup_email_address_edit
import java.util.regex.Pattern


@Keep
open class WalletDialog {
    fun chooseAnimation() {
        TODO("Not yet implemented")
    }

    class Builder(
        @NonNull private val activity: Activity,
        @NonNull private val dialogStyle: WalletDialogStyle,
        @NonNull private val dialogType: WalletDialogType
    ) {

        lateinit var alertDialog: AlertDialog
        private val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)

        private var title: String = "Title"
        private var message: String = "Message"

        // Optional features
        private var isDarkMode: Boolean = false
        private var isCancelable: Boolean = true
        private var duration: Int = 0
        private var gravity: Int = Gravity.NO_GRAVITY
        private var animation: WalletDialogAnimation = WalletDialogAnimation.DEFAULT
        private var buttonColor: Int = ContextCompat.getColor(activity, R.color.button_default)
        private var headerColor: Int = ContextCompat.getColor(activity, R.color.header_default)
        private var icon: Drawable? = null

        private lateinit var layoutView: View
        private var onClickListener: WalletDialogOnClickListener =
            object : WalletDialogOnClickListener {
                override fun onClick(dialog: Builder) {
                    dialog.dismiss()
                }
            }
        /**
         * Set dialog title text
         *
         * @param title
         * @return this, for chaining.
         */
        @NonNull
        fun setTitle(@NonNull title: String): Builder {
            this.title = title
            return this
        }

        /**
         * Set dialog message text
         *
         * @param message
         * @return this, for chaining.
         */
        @NonNull
        fun setMessage(@NonNull message: String): Builder {
            this.message = message
            return this
        }

        /**
         * Set dialog mode. Defined by default to false
         *
         * @param isDarkMode
         * @return this, for chaining.
         */
        @NonNull
        fun setDarkMode(@NonNull isDarkMode: Boolean): Builder {
            this.isDarkMode = isDarkMode
            return this
        }

        /**
         * Set an OnClickListener to the dialog
         *
         * @param onDialogClickListener interface for callback event on click of button.
         * @return this, for chaining.
         */
        @NonNull
        fun setOnClickListener(onDialogClickListener: WalletDialogOnClickListener): Builder {
            this.onClickListener = onDialogClickListener
            return this
        }

        /**
         * Define if the dialog is cancelable
         *
         * @param isCancelable
         * @return this, for chaining.
         */
        @NonNull
        fun setCancelable(isCancelable: Boolean): Builder {
            this.isCancelable = isCancelable
            return this
        }

        /**
         * Define the display duration of the dialog
         *
         * @param duration in milliseconds
         * @return this, for chaining.
         */
        @NonNull
        fun setDuration(duration: Int): Builder {
            if (duration != 0) {
                this.duration = duration
                Handler().postDelayed({
                    this.dismiss()
                }, duration.toLong())
            }
            return this
        }

        /**
         * Set the gravity of the dialog
         *
         * @param gravity in milliseconds
         * @return this, for chaining.
         */
        @NonNull
        fun setGravity(gravity: Int): Builder {
            this.gravity = gravity
            return this
        }

        /**
         * Set the animation of the dialog
         *
         * @param animation in milliseconds
         * @return this, for chaining.
         */
        @NonNull
        fun setAnimation(animation: WalletDialogAnimation): Builder {
            this.animation = animation
            return this
        }

        /**
         * Dismiss the dialog
         *
         * @return Wallet Dialog instance.
         */
        @NonNull
        fun dismiss(): WalletDialog {
            if (alertDialog.isShowing) {
                alertDialog.dismiss()
            }
            return WalletDialog()
        }


        /**
         * Set header color for dialog
         *
         * @param color
         * @return this, for chaining.
         */
        @NonNull
        fun setHeaderColor(@NonNull headerColor: Int): Builder {
            this.headerColor = headerColor
            return this
        }

        /**
         * Set button color for dialog
         *
         * @param color
         * @return this, for chaining.
         */
        @NonNull
        fun setButtonColor(@NonNull buttonColor: Int): Builder {
            this.buttonColor = buttonColor
            return this
        }

        /**
         * Choose the dialog animation according to the parameter
         *
         */
        @NonNull
        public fun chooseAnimation() {
            when (animation) {
                WalletDialogAnimation.ZOOM -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationZoom
                }
                WalletDialogAnimation.FADE -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationFade
                }
                WalletDialogAnimation.CARD -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationCard
                }
                WalletDialogAnimation.SHRINK -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationShrink
                }
                WalletDialogAnimation.SWIPE_LEFT -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationSwipeLeft
                }
                WalletDialogAnimation.SWIPE_RIGHT -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationSwipeRight
                }
                WalletDialogAnimation.IN_OUT -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationInOut
                }
                WalletDialogAnimation.SPIN -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationSpin
                }
                WalletDialogAnimation.SPLIT -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimationSplit
                }
                WalletDialogAnimation.DIAGONAL -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationDiagonal
                }
                WalletDialogAnimation.WINDMILL -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationWindMill
                }
                WalletDialogAnimation.SLIDE_UP -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationSlideUp
                }
                WalletDialogAnimation.SLIDE_DOWN -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationSlideDown
                }
                WalletDialogAnimation.SLIDE_LEFT -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationSlideLeft
                }
                WalletDialogAnimation.SLIDE_RIGHT -> {
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnimationSlideRight
                }
                WalletDialogAnimation.DEFAULT -> {
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
                }
            }
        }


        /**
         * Displays the dialog according to the parameters of the Builder
         *
         * @return Wallet Dialog instance.
         */
        @NonNull
        fun show(): WalletDialog {
            when (dialogStyle) {
                WalletDialogStyle.FORGOT_PASSWORD -> {
                    openForgotPasswordDialog()
                }
                WalletDialogStyle.SIGNIN_EMAIL -> {
                    val layoutView: View = activity.layoutInflater.inflate(R.layout.dialog_signup, null)

                    // GET COMPONENTS
                    val mainComponent = layoutView.main_signin
                    val header: RelativeLayout = layoutView.layout_header_signin
                    val imgClose: AppCompatImageView = layoutView.image_close_signin
                    val icon: AppCompatImageView = layoutView.img_icon_logo
                    val textTitle: AppCompatTextView = layoutView.signin_title
                    val textSubtitle: AppCompatTextView = layoutView.signin_subtitle
                    val textSecured: AppCompatTextView = layoutView.signup_secured_info
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
                    textSecured.text = activity.getString(R.string.signup_secured)
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

                    header.setBackgroundColor(headerColor)

                    // SET ICON
                    if (isDarkMode) {
                        val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_dark)
                        mainComponent.setBackgroundColor(backgroundColor)
                        icon.setBackgroundResource(R.drawable.img_signin_info_logo_dark_radius)
                        val textColor =  ContextCompat.getColor(activity, R.color.white)

                        textTitle.setTextColor(textColor)
                        textSubtitle.setTextColor(textColor)
                        textSecured.setTextColor(textColor)

                        emailTextInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
                        emailEditText.setTextColor(textColor)

                        passwordTexTInput.setEndIconTintList(ColorStateList.valueOf(textColor))
                        passwordEditText.setTextColor(textColor)
                        passwordTexTInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
                    } else {
                        val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_light)
                        mainComponent.setBackgroundColor(backgroundColor)

                        icon.setBackgroundResource(R.drawable.img_signin_info_logo_light_radius)
                        val textColor =  ContextCompat.getColor(activity, R.color.neutral_darker)

                        textTitle.setTextColor(textColor)
                        textSubtitle.setTextColor(textColor)
                        textSecured.setTextColor(textColor)
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

                    imgClose.setOnClickListener { onClickListener.onClick(this) }
                }

                WalletDialogStyle.SIGNIN_INFO -> {
                    layoutView = activity.layoutInflater.inflate(R.layout.dialog_signin_info, null)

                    // GET COMPONENTS
                    val mainComponent = layoutView.main_signin_info
                    val imgClose: AppCompatImageView = layoutView.image_close_signin_info
                    val icon: AppCompatImageView = layoutView.img_icon_logo
                    val textTitle: AppCompatTextView = layoutView.signin_info_title
                    val textInfoLogin: AppCompatTextView = layoutView.signin_info_login
                    val textInfoSecurity: AppCompatTextView = layoutView.signin_info_security
                    val textInfoPolicies: AppCompatTextView = layoutView.signin_info_policies
                    val checkBox: CheckBox = layoutView.signin_info_checkbox
                    val signinInfoLoginIcon = layoutView.signin_info_login_icon
                    val signinInfoGuardIcon = layoutView.signin_info_guard_icon
                    val continueButton: AppCompatButton = layoutView.btn_action_continue
                    val header: RelativeLayout = layoutView.layout_header_signin_info

                    // SET TEXTS
                    textTitle.text = activity.getString(R.string.signin_info_title)
                    textInfoLogin.text = activity.getString(R.string.signin_info_login)
                    textInfoSecurity.text = activity.getString(R.string.signin_info_security)
                    textInfoPolicies.text = activity.getString(R.string.signin_info_policies)
                    continueButton.text = activity.getString(R.string.signin_info_continue_button)
                    checkBox.text = activity.getString(R.string.signin_info_checkbox)

                    // SET COLORS
                    signinInfoLoginIcon.setColorFilter(buttonColor, android.graphics.PorterDuff.Mode.SRC_IN)
                    signinInfoGuardIcon.setColorFilter(buttonColor, android.graphics.PorterDuff.Mode.SRC_IN)
                    header.setBackgroundColor(headerColor)

                    // SET ICON
                    if (isDarkMode) {
                        val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_dark)
                        mainComponent.setBackgroundColor(backgroundColor)
                        icon.setBackgroundResource(R.drawable.img_signin_info_logo_dark_radius)
                        val textColor =  ContextCompat.getColor(activity, R.color.white)

                        textTitle.setTextColor(textColor)
                        textInfoLogin.setTextColor(textColor)
                        textInfoSecurity.setTextColor(textColor)
                        textInfoPolicies.setTextColor(textColor)
                        checkBox.setTextColor(textColor)
                    } else {
                        val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_light)
                        mainComponent.setBackgroundColor(backgroundColor)

                        icon.setBackgroundResource(R.drawable.img_signin_info_logo_light_radius)
                        val textColor =  ContextCompat.getColor(activity, R.color.neutral_darker)

                        textTitle.setTextColor(textColor)
                        textInfoLogin.setTextColor(textColor)
                        textInfoSecurity.setTextColor(textColor)
                        textInfoPolicies.setTextColor(textColor)
                        checkBox.setTextColor(textColor)
                    }
                    dialogBuilder.setView(layoutView)
                    alertDialog = dialogBuilder.create()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.window?.setGravity(Gravity.CENTER)
                    this.chooseAnimation()
                    alertDialog.show()

                    continueButton.setOnClickListener {
                        onClickListener.onClick(this)
                        //openSigninEmailDialog()
                    }

                    imgClose.setOnClickListener { onClickListener.onClick(this) }
                }
            }

            alertDialog.setCancelable(isCancelable)
            if (gravity != Gravity.NO_GRAVITY) {
                alertDialog.window?.setGravity(gravity)
            }
            return WalletDialog()
        }

        private fun openForgotPasswordDialog() {
            layoutView = activity.layoutInflater.inflate(R.layout.dialog_forgot_password, null)

            // GET COMPONENTS
            val mainComponent = layoutView.main
            val header: RelativeLayout = layoutView.layout_header
            val imgClose: AppCompatImageView = layoutView.image_close
            val icon: AppCompatImageView = layoutView.img_icon_logo
            val textTitle: AppCompatTextView = layoutView.title
            val textSubtitle: AppCompatTextView = layoutView.signin_subtitle
           /* val textSecured: AppCompatTextView = layoutView.signup_secured_info
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
            textSecured.text = activity.getString(R.string.signup_secured)
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

            header.setBackgroundColor(headerColor)

            // SET ICON
            if (isDarkMode) {
                val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_dark)
                mainComponent.setBackgroundColor(backgroundColor)
                icon.setBackgroundResource(R.drawable.img_signin_info_logo_dark_radius)
                val textColor =  ContextCompat.getColor(activity, R.color.white)

                textTitle.setTextColor(textColor)
                textSubtitle.setTextColor(textColor)
                textSecured.setTextColor(textColor)

                emailTextInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
                emailEditText.setTextColor(textColor)

                passwordTexTInput.setEndIconTintList(ColorStateList.valueOf(textColor))
                passwordEditText.setTextColor(textColor)
                passwordTexTInput.placeholderTextColor = (ContextCompat.getColorStateList(activity, R.color.white))
            } else {
                val backgroundColor = ContextCompat.getColor(activity, R.color.signin_info_background_light)
                mainComponent.setBackgroundColor(backgroundColor)

                icon.setBackgroundResource(R.drawable.img_signin_info_logo_light_radius)
                val textColor =  ContextCompat.getColor(activity, R.color.neutral_darker)

                textTitle.setTextColor(textColor)
                textSubtitle.setTextColor(textColor)
                textSecured.setTextColor(textColor)
            }*/
            dialogBuilder.setView(layoutView)
            alertDialog = dialogBuilder.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)

            this.chooseAnimation()
            alertDialog.show()

            imgClose.setOnClickListener { onClickListener.onClick(this) }
        }
    }
}