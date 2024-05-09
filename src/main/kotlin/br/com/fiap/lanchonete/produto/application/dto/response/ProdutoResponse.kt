package br.com.fiap.lanchonete.produto.application.dto.response

import br.com.fiap.lanchonete.produto.domain.entities.enums.CategoriaEnum
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal


data class ProdutoResponse(

        @field:NotBlank
        var id: Long,
        @field:NotBlank
        var nome: String,
        @field:NotNull
        var categoria: CategoriaEnum,
        @field:NotBlank
        var descricao: String,
        @field:NotNull
        var preco: BigDecimal,
        @field:NotEmpty
        var imagens: List<String>
)