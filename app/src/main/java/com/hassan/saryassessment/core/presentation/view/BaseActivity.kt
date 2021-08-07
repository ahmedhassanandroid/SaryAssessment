package com.hassan.saryassessment.core.presentation.view

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(changeLoc(newBase))
    }

    private fun changeLoc(
        context: Context?
    ): Context? {
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val resources = context?.resources
        val configuration =
            Configuration(resources?.configuration)
        configuration.setLocale(locale)
        return context?.createConfigurationContext(configuration)
    }
}