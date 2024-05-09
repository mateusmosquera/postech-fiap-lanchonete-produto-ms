package br.com.fiap.lanchonete.produto.exception.enums

import br.com.fiap.lanchonete.produto.exception.dto.ResponseErrorDto

interface ExceptionEnum {
    fun getResponseError(): ResponseErrorDto
}