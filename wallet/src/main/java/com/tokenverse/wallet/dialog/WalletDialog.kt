package com.tokenverse.wallet.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.Gravity
import android.view.View
import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.R
import com.tokenverse.wallet.dialog.types.WalletDialogForgotPassword
import com.tokenverse.wallet.dialog.types.WalletDialogInfo
import com.tokenverse.wallet.dialog.types.WalletDialogLogin
import com.tokenverse.wallet.dialog.types.WalletDialogSignupEmail
import kotlinx.android.synthetic.main.dialog_forgot_password.view.*
import kotlinx.android.synthetic.main.dialog_signin_info.view.*
import kotlinx.android.synthetic.main.dialog_signup.view.*

@Keep
open class WalletDialog {
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

        fun getActivity(): Activity {
            return activity
        }

        fun getIsDarkMode(): Boolean {
            return isDarkMode
        }

        fun getHeaderColor(): Int {
            return headerColor
        }

        fun getOnClickListener(): WalletDialogOnClickListener {
            return onClickListener
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
            var laoutView: View? = null

            when (dialogStyle) {
                WalletDialogStyle.FORGOT_PASSWORD -> {
                    layoutView = WalletDialogForgotPassword(this).constroy()
                }
                WalletDialogStyle.SIGNIN_EMAIL -> {
                    layoutView = WalletDialogSignupEmail(this).constroy()
                }
                WalletDialogStyle.SIGNIN_INFO -> {
                    layoutView = WalletDialogInfo(this).constroy()
                }
                WalletDialogStyle.LOGIN -> {
                    layoutView = WalletDialogLogin(this).constroy()
                }
            }
            layoutView.let {
                dialogBuilder.setView(layoutView)
                alertDialog = dialogBuilder.create()
                alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.window?.setGravity(Gravity.CENTER)
                this.chooseAnimation()
                alertDialog.show()
            }

            alertDialog.setCancelable(isCancelable)
            if (gravity != Gravity.NO_GRAVITY) {
                alertDialog.window?.setGravity(gravity)
            }
            return WalletDialog()
        }
    }
}