package br.com.fiap.lanchonete.produto.client

import feign.okhttp.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {

    @Bean
    fun client(): OkHttpClient = OkHttpClient()
}