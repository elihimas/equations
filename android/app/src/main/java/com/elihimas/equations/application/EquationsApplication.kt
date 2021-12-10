package com.elihimas.equations.application

import android.app.Application
import com.elihimas.equations.BuildConfig
import com.elihimas.equations.util.GameTimer
import com.elihimas.equations.util.GameTimerImpl
import com.elihimas.equations.viewmodels.GameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

val equationsModules = module {
    factory<GameTimer> { GameTimerImpl() }
    viewModel { GameViewModel(get()) }
}

class EquationsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@EquationsApplication)
            modules(equationsModules)
        }

    }
}