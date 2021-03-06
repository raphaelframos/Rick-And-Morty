package com.renatoramos.rickandmort

import android.app.Application
import com.renatoramos.rickandmort.common.modular.di.DaggerAppComponent
import com.renatoramos.rickandmort.common.modular.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.paperdb.Paper
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initDagger()
        initPaperDb()
    }

    private fun initDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .applicationModule(ApplicationModule(this))
            .build()
            .inject(this)
    }

    private fun initPaperDb(){
        Paper.init(this)
    }

}