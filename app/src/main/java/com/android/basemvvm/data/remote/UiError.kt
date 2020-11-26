package com.android.basemvvm.data.remote

import com.android.basemvvm.empty.R

data class UiError(
    var icon: Int = 0,
    var title: String,
    var message: String? = null,
    var actionString: String? = null
)