package com.example.zpi_mobile.http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class KtorHttpClient {

    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    fun getHttpClient(): HttpClient {
        return httpClient
    }

}