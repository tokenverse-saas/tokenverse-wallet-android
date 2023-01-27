package com.tokenverse.wallet.uikit

import android.content.Context
import android.graphics.PorterDuff
import android.provider.Settings.Global.getString
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tokenverse.wallet.R
import com.tokenverse.wallet.databinding.SecuredInfoViewBinding
import com.tokenverse.wallet.extensions.getStyledAttributes
import com.tokenverse.wallet.extensions.headerColor
import com.tokenverse.wallet.extensions.neutralDarker
import com.tokenverse.wallet.extensions.white

class SecuredInfoView: ConstraintLayout {

    private lateinit var binding: SecuredInfoViewBinding

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private var securedInfoText: String? = null
    private var isDarkMode: Boolean = false

    private fun initView(attrs: AttributeSet?) {
        context.getStyledAttributes(attrs, R.styleable.SecuredInfoView) {
            securedInfoText = getString(R.styleable.SecuredInfoView_securedText)
        }
        init()
    }

    fun set(text: String) {
        binding.tvSecuredInfo.text = text
    }

    fun set(iconSize: Int) {
        binding.imageView.layoutParams.height = iconSize
    }

    fun setDarkMode(isDarkMode: Boolean) {
        this.isDarkMode = isDarkMode

        if (isDarkMode) {
            binding.tvSecuredInfo.setTextColor(context.white)
        }else {
            binding.tvSecuredInfo.setTextColor(context.neutralDarker)
        }
    }

    private fun init() {
        binding = SecuredInfoViewBinding.inflate(LayoutInflater.from(context), this, true)

        binding.tvSecuredInfo.apply {
            text = text
            securedInfoText?.let {
                set(text = it)
            }
        }

    }
}