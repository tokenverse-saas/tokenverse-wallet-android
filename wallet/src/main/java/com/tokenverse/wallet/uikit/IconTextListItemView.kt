package com.tokenverse.wallet.uikit

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tokenverse.wallet.R
import com.tokenverse.wallet.databinding.IconTextListItemViewBinding
import com.tokenverse.wallet.extensions.*

class IconTextListItemView: ConstraintLayout {

    private lateinit var binding: IconTextListItemViewBinding

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private var icon: Drawable? = null
    private var text: String? = null
    private var isDarkMode: Boolean = false

    private fun initView(attrs: AttributeSet?) {
        context.getStyledAttributes(attrs, R.styleable.IconTextListItemView) {
            icon = getDrawable(R.styleable.IconTextListItemView_icon)
            text = getString(R.styleable.IconTextListItemView_text)
        }
        init()
    }

    fun setDarkMode(isDarkMode: Boolean) {
        this.isDarkMode = isDarkMode

        if (isDarkMode) {
            binding.ivIcon.setColorFilter(context.white, PorterDuff.Mode.SRC_IN)
            binding.tvText.setTextColor(context.white)
        }else {
            binding.ivIcon.setColorFilter(context.headerColor, PorterDuff.Mode.SRC_IN)
            binding.tvText.setTextColor(context.neutralDarker)
        }
    }

    private fun init() {
        binding = IconTextListItemViewBinding.inflate(LayoutInflater.from(context), this, true)

        icon?.let {
            binding.ivIcon.setImageDrawable(it)
        }

        text.let {
            binding.tvText.text = it
        }
    }
}