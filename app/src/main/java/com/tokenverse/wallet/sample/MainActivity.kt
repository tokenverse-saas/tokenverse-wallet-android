package com.tokenverse.wallet.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.dialog.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rbLight: RadioButton
    private lateinit var rbDark: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rbLight = findViewById(R.id.rg_light)
        rbDark = findViewById(R.id.rg_dark)
    }

    override fun onClick(view: View) {
        val headerColor = ContextCompat.getColor(this, R.color.dialogHeaderColor)
        val buttonColor = ContextCompat.getColor(this, R.color.dialogHeaderColor)

        when (view.id) {
            R.id.btn_signin_info -> {

                WalletDialog.Builder(this, WalletDialogStyle.SIGNIN_INFO, WalletDialogType.INFO)
                    .setDarkMode(rbDark.isChecked)
                    .setAnimation(WalletDialogAnimation.DEFAULT)
                    .setOnClickListener(object : WalletDialogOnClickListener {
                        override fun onClick(dialog: WalletDialog.Builder) {
                            dialog.dismiss()
                        }
                    })
                    .show()
            }

            R.id.btn_forgot_password -> {
                WalletDialog.Builder(this, WalletDialogStyle.FORGOT_PASSWORD, WalletDialogType.INFO)
                    .setDarkMode(rbDark.isChecked)
                    .setAnimation(WalletDialogAnimation.DEFAULT)
                    .setOnClickListener(object : WalletDialogOnClickListener {
                        override fun onClick(dialog: WalletDialog.Builder) {
                            dialog.dismiss()
                        }
                    })
                    .show()
            }

            R.id.btn_signup -> {
                WalletDialog.Builder(this, WalletDialogStyle.SIGNIN_EMAIL, WalletDialogType.INFO)
                    .setDarkMode(rbDark.isChecked)
                    .setAnimation(WalletDialogAnimation.DEFAULT)
                    .setOnClickListener(object : WalletDialogOnClickListener {
                        override fun onClick(dialog: WalletDialog.Builder) {
                            dialog.dismiss()
                        }
                    })
                    .show()
            }

            R.id.btn_login -> {
                WalletDialog.Builder(this, WalletDialogStyle.LOGIN, WalletDialogType.INFO)
                    .setDarkMode(rbDark.isChecked)
                    .setAnimation(WalletDialogAnimation.DEFAULT)
                    .setOnClickListener(object : WalletDialogOnClickListener {
                        override fun onClick(dialog: WalletDialog.Builder) {
                            dialog.dismiss()
                        }
                    })
                    .show()
            }
        }
    }
}