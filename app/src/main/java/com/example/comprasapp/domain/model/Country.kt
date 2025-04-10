package com.example.comprasapp.domain.model

import java.io.Serializable

/**
 * Enumeración que define los países disponibles para la aplicación.
 * FAKESTORE representa la API de FakeStore (País A).
 * PLATZI representa la API de Platzi (País B).
 */
enum class Country : Serializable {
    FAKESTORE,
    PLATZI
}