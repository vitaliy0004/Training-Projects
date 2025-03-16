package com.example.m_2_7_presentation_patterns.presentation


import com.example.core.FactoryViewModel
import com.example.core.navigation.NavigationRouter
import com.example.navigation.NavigationRouterImp
import com.example.network.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {
    @Provides
    fun provideFactory(city: Repository): FactoryViewModel {
        return FactoryViewModel(city)
    }

    @Provides
    fun provideNavigationRouter(): NavigationRouter {
        return NavigationRouterImp()
    }
}