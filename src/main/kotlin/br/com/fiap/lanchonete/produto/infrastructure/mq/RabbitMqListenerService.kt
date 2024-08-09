package br.com.fiap.lanchonete.produto.infrastructure.mq

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class RabbitMqListenerService() {

    private val logger = LoggerFactory.getLogger(RabbitMqListenerService::class.java)

    @RabbitListener(queues = ["\${app.mq.cozinha.queueName}"])
    fun listen(message: String) {

        logger.info("Received Message: $message")

    }
}