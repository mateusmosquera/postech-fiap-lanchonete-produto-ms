package br.com.fiap.lanchonete.produto.infrastructure.web.controller

import br.com.fiap.lanchonete.produto.LanchoneteProdutoApplication
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@CucumberContextConfiguration
@SpringBootTest(classes = [LanchoneteProdutoApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class CucumberConfiguration {
}