package com.tokenverse.wallet.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.tokenverse.wallet.R
import com.tokenverse.wallet.databinding.CheckboxTextViewBinding
import com.tokenverse.wallet.databinding.HeaderViewBinding
import com.tokenverse.wallet.dialog.WalletDialog
import com.tokenverse.wallet.extensions.getStyledAttributes
import com.tokenverse.wallet.extensions.neutralDarker
import com.tokenverse.wallet.extensions.white
enum class CheckboxTextAlignment(val checkboxTextAligment: String) {
    LEFT("left"),
    RIGHT("right"),
    CENTER("center")
}

class CheckBoxTextView : ConstraintLayout {
    private lateinit var binding: CheckboxTextViewBinding
    private var CHECKBOX_COLOR_DEFAULT: Int =
        ContextCompat.getColor(context, R.color.checkbox_default)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private var checkboxText: String? = null
    private var checkboxColor: Int? = null
    private var checkboxTextAlignment: String? = null
    private var isDarkMode: Boolean = false

    private fun initView(attrs: AttributeSet?) {
        context.getStyledAttributes(attrs, R.styleable.CheckboxTextView) {
            checkboxColor =
                getColor(R.styleable.CheckboxTextView_checkboxColor, CHECKBOX_COLOR_DEFAULT)
            checkboxText = getString(R.styleable.CheckboxTextView_checkboxText)
            checkboxTextAlignment = getString(R.styleable.CheckboxTextView_checkboxTextAlignment)
        }
        init()
    }

    private fun setCheckboxColor(checkboxColor: Int) {
        binding.checkbox.buttonTintList = (ContextCompat.getColorStateList(context, checkboxColor))
    }

    private fun setCheckboxText(checkboxText: String) {
        binding.checkbox.text = checkboxText
    }

    private fun setCheckboxAlignment(checkboxAlignment: String) {
        /*when (CheckboxTextAlignment.valueOf(checkboxAlignment)) {
            CheckboxTextAlignment.CENTER -> binding.clCheckboxTextView. = TEXT_ALIGNMENT_CENTER
            CheckboxTextAlignment.LEFT -> binding.clCheckboxTextView.textAlignment = TEXT_ALIGNMENT_TEXT_START
            CheckboxTextAlignment.RIGHT -> binding.clCheckboxTextView.textAlignment = TEXT_ALIGNMENT_TEXT_END
        }*/
    }

    fun setDarkMode(isDarkMode: Boolean) {
        this.isDarkMode = isDarkMode

        if (isDarkMode) {
            binding.checkbox.setTextColor(context.white)
        } else {
            binding.checkbox.setTextColor(context.neutralDarker)
        }
    }

    fun setCheckBoxOnClickListener(builder: WalletDialog.Builder) {
        //binding.imHeaderClose.setOnClickListener { builder.dismiss() }
    }

    private fun init() {
        binding = CheckboxTextViewBinding.inflate(LayoutInflater.from(context), this, true)

        binding.checkbox.apply {
            //checkboxColor?.let { setCheckboxColor(it) }
            checkboxText?.let { setCheckboxText(it) }
            checkboxTextAlignment?.let { setCheckboxAlignment(it) }
        }
    }

}