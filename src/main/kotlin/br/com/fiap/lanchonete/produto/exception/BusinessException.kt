package br.com.fiap.lanchonete.produto.exception

import br.com.fiap.lanchonete.produto.exception.enums.ExceptionEnum

class BusinessException(val exceptionEnum: ExceptionEnum,
                        val messages: List<String> = emptyList()) : Exception()