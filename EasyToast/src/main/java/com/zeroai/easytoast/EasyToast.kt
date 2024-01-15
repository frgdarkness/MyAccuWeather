package com.zeroai.easytoast

import android.content.Context
import android.widget.Toast

object EasyToast {

    fun showShortToast(msg: String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}