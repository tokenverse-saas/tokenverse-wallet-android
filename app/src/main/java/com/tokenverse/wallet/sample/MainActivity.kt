package com.tokenverse.wallet.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.dialog.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

        private lateinit var rbSigninInfoLight: RadioButton
        private lateinit var rbSigninInfoDark: RadioButton

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            rbSigninInfoLight = findViewById(R.id.rg_signinInfo_light)
            rbSigninInfoDark = findViewById(R.id.rg_signinInfo_dark)
        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.btn_signin_info -> {
                    val headerColor = ContextCompat.getColor(this, R.color.dialogHeaderColor)
                    val buttonColor = ContextCompat.getColor(this, R.color.dialogHeaderColor)

                    WalletDialog.Builder(this, WalletDialogStyle.SIGNIN_INFO, WalletDialogType.INFO)
                        .setDarkMode(rbSigninInfoDark.isChecked)
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