package br.com.fiap.lanchonete.produto

import io.cucumber.core.options.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features/produto.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "br.com.fiap.lanchonete.produto.integration")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, html:target/cucumber-report/cucumber.html")
class RunCucumberTest  {
}