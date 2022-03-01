package com.ismita.chromerivals.utils.extensions

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import com.google.android.material.chip.Chip
import com.ismita.chromerivals.R

object ChipsExtension {

    @SuppressLint("ResourceAsColor")
    fun Chip.checkBlackChip() {
        this.setChipBackgroundColorResource(R.color.black)
        this.setTextAppearanceResource(R.style.events_chip_selected)
    }

    @SuppressLint("ResourceType")
    fun Chip.unCheckBlackChip() {
        this.setChipBackgroundColorResource(R.color.white)
        this.setTextColor(R.color.black)
        this.setTextAppearanceResource(R.style.events_chip_not_selected)
    }

    @SuppressLint("ResourceAsColor")
    fun Chip.checkBlueChip() {
        this.chipBackgroundColor = ColorStateList.valueOf(R.color.app_primary)
        this.setTextColor(R.color.white)
    }

    @SuppressLint("ResourceAsColor")
    fun Chip.unCheckBlueChip() {
        this.chipBackgroundColor = ColorStateList.valueOf(R.color.white)
        this.setTextColor(R.color.app_primary)
    }

}