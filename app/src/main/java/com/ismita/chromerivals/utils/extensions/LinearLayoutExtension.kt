package com.ismita.chromerivals.utils.extensions

import androidx.recyclerview.widget.LinearLayoutManager

object LinearLayoutExtension {

    fun LinearLayoutManager.toHorizontalLayoutManager(): LinearLayoutManager {
        this.orientation = LinearLayoutManager.HORIZONTAL
        return this
    }

    fun LinearLayoutManager.toVerticalLayoutManager(): LinearLayoutManager {
        this.orientation = LinearLayoutManager.HORIZONTAL
        return this
    }

}