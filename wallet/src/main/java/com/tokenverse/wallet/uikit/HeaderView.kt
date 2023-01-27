package com.tokenverse.wallet.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.R
import com.tokenverse.wallet.databinding.HeaderViewBinding
import com.tokenverse.wallet.dialog.WalletDialog
import com.tokenverse.wallet.extensions.getStyledAttributes

class HeaderView: ConstraintLayout {

    private lateinit var binding: HeaderViewBinding
    private var HEADER_COLOR_DEFAULT: Int = ContextCompat.getColor(context, R.color.header_default)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private var headerColor: Int? = null
    private var isDarkMode: Boolean = false

    private fun initView(attrs: AttributeSet?) {
        context.getStyledAttributes(attrs, R.styleable.HeaderView) {
            headerColor = getColor(R.styleable.HeaderView_headerColor, HEADER_COLOR_DEFAULT)
        }
        init()
    }

    fun setHeaderColor(headerColor: Int) {
        binding.rlHeader.setBackgroundColor(headerColor)
    }

    fun setDarkMode(isDarkMode: Boolean) {
        this.isDarkMode = isDarkMode

        if (isDarkMode) {
            binding.imLogo.setBackgroundResource(R.drawable.img_signin_info_logo_dark_radius)
        }else {
            binding.imLogo.setBackgroundResource(R.drawable.img_signin_info_logo_light_radius)
        }
    }

    fun setButtonCloseOnClickListener(builder: WalletDialog.Builder) {
        binding.imHeaderClose.setOnClickListener { builder.dismiss() }
    }

    private fun init() {
        binding = HeaderViewBinding.inflate(LayoutInflater.from(context), this, true)

        binding.rlHeader.apply {
            headerColor?.let { setHeaderColor(it) }
        }
    }
}