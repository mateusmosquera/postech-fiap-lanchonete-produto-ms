package br.com.fiap.lanchonete.produto.integration

import br.com.fiap.lanchonete.produto.application.dto.request.ImagemRequest
import br.com.fiap.lanchonete.produto.application.dto.request.ProdutoRequest
import br.com.fiap.lanchonete.produto.application.dto.response.ProdutoResponse
import br.com.fiap.lanchonete.produto.client.ProdutoCucumberClient
import br.com.fiap.lanchonete.produto.domain.entities.enums.CategoriaEnum
import feign.FeignException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.math.BigDecimal
import kotlin.test.assertNull

class ProdutoHttpControllerSteps(var produtoCucumberClient: ProdutoCucumberClient){

    private lateinit var produtoRequest: ProdutoRequest
    private lateinit var produtoResponse: ResponseEntity<ProdutoResponse>
    private lateinit var pageCategoriaResponse: ResponseEntity<Page<ProdutoResponse>>
    private lateinit var unitResponse: ResponseEntity<Unit>

    private lateinit var imagem:List<String>

    @Given("^que o cliente envia uma solicitacao para criar um novo produto com nome ([^\"]*), descricao ([^\"]*) e preco (\\d+)")
    fun givenQueOClienteEnviaUmaSolicitacaoParaCriarUmNovoProdutoComOsDados(nome: String, descricao:String, preco:Double) {

        produtoRequest = ProdutoRequest(nome = nome, categoria = CategoriaEnum.LANCHE, descricao = descricao, preco = BigDecimal.valueOf(preco))

        try {
            produtoResponse = produtoCucumberClient.create(produtoRequest)
        }  catch (e: FeignException){
            produtoResponse = ResponseEntity(HttpStatus.valueOf(e.status()))
        }

    }

    @When("^cliente recebe a solicitacao com codigo de status (\\d+)")
    fun whenClienteRecebeASolicitacaoComCodigoDeStatus(status: Int) {
        assertEquals(produtoResponse.statusCode.value(), status)
    }

    @Then("^o cliente recebe a resposta com os mesmos dados do produto criado, com nome ([^\"]*), descricao ([^\"]*) e preco (\\d+)")
    fun thenOClienteRecebeARespostaComOsMesmosDadosDoProdutoCriado(nome: String, descricao:String, preco:Double) {

        if(produtoResponse.statusCode.value() == 201) {
            assertEquals( ProdutoResponse(1L,nome, CategoriaEnum.LANCHE, descricao,  BigDecimal.valueOf(preco), listOf()), produtoResponse.body)
        }
    }

    @Given("^que o cliente envia uma solicitacao para recuperar informacoes de um produto pelo ID (\\d+)$")
    fun givenQueOClienteEnviaUmaSolicitacaoParaRecuperarInformacoesDeUmProdutoPeloId(id: Long) {
        try {
            produtoResponse = produtoCucumberClient.getProduto(id)
        } catch (e: FeignException){
            produtoResponse = ResponseEntity(HttpStatus.valueOf(e.status()))
        }
    }

    @Then("^recebe as informacoes corretas do produto na resposta$")
    fun thenRecebeAsInformacoesCorretasDoProdutoNaResposta() {

        if(produtoResponse.statusCode.value() == 200) {
            val produtoRetornado = produtoResponse.body
            assertNotNull(produtoRetornado)
        }
    }

    @Given("^que o cliente envia uma solicitacao para editar informacoes de um produto pelo ID (\\d+) com os dados novos")
    fun givenQueOClienteEnviaUmaSolicitacaoParaEditarInformacoesDeUmProdutoPeloIdComOsNovosDados(id: Long) {
        produtoResponse = produtoCucumberClient.editProduto(id, ProdutoRequest(nome = "nome atualizado", categoria = CategoriaEnum.LANCHE, descricao = "descricao atualizada", preco = BigDecimal.valueOf(20)))
    }

    @Then("^o cliente recebe a resposta de ID (\\d+) com os novos dados do produto")
    fun thenOClienteRecebeARespostaComOsNovosDadosDoProduto(id: Long) {

        if(produtoResponse.statusCode.value() == 200) {
            assertEquals( ProdutoResponse(id, "nome atualizado", CategoriaEnum.LANCHE,  "descricao atualizada",  BigDecimal.valueOf(20), listOf()), produtoResponse.body)
        }
    }

    @Given("^que o cliente envia uma solicitacao para deletar um produto pelo ID (\\d+)$")
    fun givenQueOClienteEnviaUmaSolicitacaoParaDeletarUmProdutoPeloId(id: Long) {
        try {
            unitResponse = produtoCucumberClient.delete(id)
        } catch (e: FeignException){
            unitResponse = ResponseEntity(HttpStatus.valueOf(e.status()))
        }
    }

    @When("^cliente recebe apenas o codigo de status (\\d+)")
    fun whenClienteRecebeApenasCodigoStatus(id:Int) {

        assertEquals(HttpStatus.valueOf(id), unitResponse.statusCode)
    }

    @Then("^o produto e removido com sucesso e o cliente recebe uma resposta vazia$")
    fun thenOProdutoERemovidoComSucessoEOClienteRecebeUmaRespostaVazia() {
        assertNull(unitResponse.body)
    }

    @Given("^que o cliente envia uma solicitacao para recuperar produtos por categoria (.*)$")
    fun givenQueOClienteEnviaUmaSolicitacaoParaRecuperarProdutosPorCategoria(categoria: String) {
        pageCategoriaResponse = produtoCucumberClient.getByCategoria(CategoriaEnum.valueOf(categoria), Pageable.ofSize(10))
    }

    @When("^cliente recebe as informacoes com o codigo (\\d+)")
    fun whenClienteRecebeAsInformacoesComCodigo(id:Int) {

        assertEquals(HttpStatus.valueOf(id), pageCategoriaResponse.statusCode)
    }

    @Then("^recebe uma lista de produtos da categoria na resposta$")
    fun thenRecebeUmaListaDeProdutosDaCategoriaNaResposta() {
        assertNotNull(pageCategoriaResponse.body)
    }


    @Given("^que o cliente envia uma solicitacao para alterar a imagem de um produto pelo ID (\\d+) com a nova imagem (.*)")
    fun givenQueOClienteEnviaUmaSolicitacaoParaAlterarAImagemDeUmProdutoPeloIdComANovaImagem(id: Long, novaImagem: String) {
        imagem = listOf(novaImagem)

        try {
            produtoResponse = produtoCucumberClient.alterarImagem(id, ImagemRequest( imagem))
        } catch (e: FeignException){
            produtoResponse = ResponseEntity(HttpStatus.valueOf(e.status()))
        }
    }

    @Then("^o cliente recebe a resposta com a imagem do produto atualizada$")
    fun thenOClienteRecebeARespostaComAImagemDoProdutoAtualizada() {
        if(produtoResponse.statusCode.value() == 200) {
            assertEquals(produtoResponse.body!!.imagens, imagem)
        }
    }
}