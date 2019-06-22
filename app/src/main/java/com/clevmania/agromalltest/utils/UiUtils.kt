package com.clevmania.agromalltest.utils

import android.content.Context
import android.widget.Toast

object UiUtils {
    fun showToast(context: Context, message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
}
