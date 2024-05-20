package com.example.dependency_injection.di.dagger

import dagger.Module
import dagger.Provides
import com.example.dependency_injection.data.FabriceBike
import com.example.dependency_injection.data.FabriceFrame
import com.example.dependency_injection.data.FabriceWheel
import javax.inject.Singleton

@Module
class Dagger {

    @Provides
    fun frame(): FabriceFrame {
        return FabriceFrame()
    }

    @Provides
    @Singleton
    fun wheel(): FabriceWheel {
        return FabriceWheel()
    }

    @Provides
    fun bike(
        wheel: FabriceWheel,
        frame: FabriceFrame
    ): FabriceBike {
        return FabriceBike(wheel, frame)
    }
}