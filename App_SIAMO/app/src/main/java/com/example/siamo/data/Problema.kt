package com.example.siamo.data

import androidx.annotation.StringRes

data class Problema(
    @StringRes val idProblemaID: Int,
    @StringRes val descripcionID: Int,
    @StringRes val detalleID: Int,
    @StringRes val idSolucionID: Int,
)
