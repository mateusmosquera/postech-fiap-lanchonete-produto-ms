package br.com.fiap.lanchonete.produto.infrastructure.mq

import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration(@Value("\${app.mq.cozinha.username}") private val username: String,
                          @Value("\${app.mq.cozinha.password}") private val password: String,
                          @Value("\${app.mq.cozinha.brokerURL}") private val host: String,
                          @Value("\${app.mq.port}") private val port: Int,
                          @Value("\${app.mq.ssl}") private val ssl: Boolean ) {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = ConnectionFactory()
        connectionFactory.username = username
        connectionFactory.password = password
        connectionFactory.host = host
        connectionFactory.port = port
        if(ssl){
            connectionFactory.useSslProtocol()
        }
        return connectionFactory
    }
}