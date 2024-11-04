package com.example.siamo

import android.app.Application
import com.example.siamo.data.AppContainer
import com.example.siamo.data.AppDataContainer

class SiamoApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Inicializa el contenedor de datos con esta aplicación
        container = AppDataContainer(this)
    }
}
