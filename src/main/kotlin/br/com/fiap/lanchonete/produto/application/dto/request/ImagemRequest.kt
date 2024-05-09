package br.com.fiap.lanchonete.produto.application.dto.request

import jakarta.validation.constraints.NotEmpty

data class ImagemRequest(
        @field:NotEmpty
        val imagens: List<String>
)