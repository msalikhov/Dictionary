package com.msalikhov.dictionary

import android.app.Application
import com.msalikhov.dictionary.domain.NetworkProvider
import com.msalikhov.dictionary.domain.PicassoProvider
import com.msalikhov.dictionary.domain.RepositoryImpl
import com.msalikhov.dictionary.view.WordDescriptionFragment
import com.msalikhov.dictionary.viewmodel.WordsSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    private val KoinApplication.fragmentsModule
        get() = module {
            fragmentFactory()
            fragment { WordDescriptionFragment(get()) }
        }

    private val repositoryModule
        get() = module {
            single { NetworkProvider.getOkHttp() }
            single { NetworkProvider.getRetrofit(get()) }
            single { NetworkProvider.getNetworkApi(get()) }
            single { RepositoryImpl.create(get()) }
            single { PicassoProvider.build(get(), get()) }
        }

    private val viewModelsModule
        get() = module {
            viewModel { WordsSearchViewModel(get()) }
        }

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                fragmentsModule,
                repositoryModule,
                viewModelsModule
            )
        }
    }
}