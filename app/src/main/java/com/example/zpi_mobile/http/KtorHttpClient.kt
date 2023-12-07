package com.example.zpi_mobile.http

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class KtorHttpClient {

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

}