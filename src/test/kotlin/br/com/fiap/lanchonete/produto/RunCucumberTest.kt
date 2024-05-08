package br.com.fiap.lanchonete.produto

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith


@RunWith(Cucumber::class)
@CucumberOptions(
    plugin = ["pretty"],
    features = ["src/test/resources/features"],
    glue = ["br.com.fiap.lanchonete.produto.infrastructure.web.controller"]
)
class RunCucumberTest  {
}