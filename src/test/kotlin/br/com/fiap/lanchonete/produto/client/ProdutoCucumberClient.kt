package br.com.fiap.lanchonete.produto.client

import br.com.fiap.lanchonete.produto.application.dto.request.ImagemRequest
import br.com.fiap.lanchonete.produto.application.dto.request.ProdutoRequest
import br.com.fiap.lanchonete.produto.application.dto.response.ProdutoResponse
import br.com.fiap.lanchonete.produto.domain.entities.enums.CategoriaEnum
import jakarta.validation.Valid
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "ProdutoCucumber",
    url = "\${server.host}:\${server.port}\${server.servlet.context-path}/produto"
)
@Service
interface ProdutoCucumberClient {


    @PostMapping
    fun create(@Valid @RequestBody produtoRequest: ProdutoRequest): ResponseEntity<ProdutoResponse>

    @GetMapping("/{id}")
    fun getProduto(@PathVariable(name = "id") id: Long): ResponseEntity<ProdutoResponse>

    @PutMapping("/{id}")
    fun editProduto(@PathVariable(name = "id") id: Long, @Valid @RequestBody produtoRequest: ProdutoRequest): ResponseEntity<ProdutoResponse>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(name = "id") id: Long): ResponseEntity<Unit>

    @GetMapping("/{categoria}/categoria")
    fun getByCategoria(@PathVariable(name = "categoria") categoria: CategoriaEnum, pageable: Pageable): ResponseEntity<Page<ProdutoResponse>>

    @PatchMapping("/{id}/imagem")
    fun alterarImagem(@PathVariable(name = "id") id: Long, @Valid @RequestBody imagensRequest: ImagemRequest): ResponseEntity<ProdutoResponse>

}