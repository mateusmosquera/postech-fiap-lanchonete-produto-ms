package br.com.fiap.lanchonete.produto.exception.enums

import br.com.fiap.lanchonete.produto.exception.dto.ResponseErrorDto

fun interface ExceptionEnum {
    fun getResponseError(): ResponseErrorDto
}