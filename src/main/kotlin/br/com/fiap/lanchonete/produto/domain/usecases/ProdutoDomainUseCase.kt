package br.com.fiap.lanchonete.produto.domain.usecases

import br.com.fiap.lanchonete.produto.application.gateway.ProdutoRepositoryGateway
import br.com.fiap.lanchonete.produto.domain.entities.Produto
import br.com.fiap.lanchonete.produto.domain.entities.enums.CategoriaEnum
import br.com.fiap.lanchonete.produto.domain.entities.exception.ProdutoExceptionEnum
import br.com.fiap.lanchonete.produto.exception.BusinessException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProdutoDomainUseCase(
        private val produtoRepositoryGateway: ProdutoRepositoryGateway
) {
    fun get(id: Long) =
            produtoRepositoryGateway.findProdutoById(id) ?:
            throw BusinessException(ProdutoExceptionEnum.PRODUTO_NOT_FOUND, messages = listOf("Produto com id=$id"))

    fun create(produto: Produto) = produtoRepositoryGateway.save(produto)

    fun delete(id: Long) = produtoRepositoryGateway.findProdutoById(id)?.let {
        produtoRepositoryGateway.delete(it)
    } ?: throw BusinessException(ProdutoExceptionEnum.PRODUTO_NOT_FOUND, messages = listOf("Produto com id=$id"))

    fun put(id: Long, produto: Produto) =
         produtoRepositoryGateway.findProdutoById(id)?.let {
            produtoRepositoryGateway.save(produto)
        } ?: throw BusinessException(ProdutoExceptionEnum.PRODUTO_NOT_FOUND, messages = listOf("Produto com id=$id"))

    fun getByCategoria(categoria: CategoriaEnum, pageable: Pageable) =
            produtoRepositoryGateway.findProdutoByCategoria(categoria, pageable).also {
                if (it.isEmpty) throw BusinessException(
                    ProdutoExceptionEnum.CATEGORIA_NOT_FOUND,
                    messages = listOf("Produto com categoria=$categoria"))
            }

    fun alterarImagem(id: Long, imagens: List<String>): Produto =
        produtoRepositoryGateway.findProdutoById(id)?.let {
            it.imagens = imagens
            produtoRepositoryGateway.save(it)
        } ?: throw BusinessException(ProdutoExceptionEnum.PRODUTO_NOT_FOUND, messages = listOf("Produto com id=$id"))

}