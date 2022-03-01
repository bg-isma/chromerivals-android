package com.ismita.chromerivals.utils

import android.content.Context
import com.google.android.material.chip.Chip
import com.ismita.chromerivals.R
import com.ismita.chromerivals.utils.extensions.ChipsExtension.checkBlackChip
import com.ismita.chromerivals.utils.extensions.ChipsExtension.unCheckBlackChip

object ChipUtils {

    fun getCustomMaterialChip(context: Context, text: String, bgColor: Int, strokeColor: Int, textAppearance: Int, strokeWidth: Float, minHeight: Float): Chip {
        val chip = Chip(context)
        chip.text = text
        chip.setChipBackgroundColorResource(bgColor)
        chip.setChipStrokeColorResource(strokeColor)
        chip.chipStrokeWidth = strokeWidth
        chip.textSize = 13f
        chip.setChipEndPaddingResource(R.dimen.end_padding)
        chip.setChipStartPaddingResource(R.dimen.start_padding)
        chip.setTextAppearanceResource(textAppearance)
        chip.chipMinHeight = minHeight
        return chip
    }

    fun selectChip(chips: List<Chip>, chipSelectedIndex: Int) {
        val chipSelected = chips[chipSelectedIndex]
        chips.forEachIndexed { actualIndex, otherChip ->
            if (actualIndex == chipSelectedIndex) chipSelected.checkBlackChip()
            else otherChip.unCheckBlackChip()
        }
    }

}