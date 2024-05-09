package br.com.fiap.lanchonete.produto.domain.entities

import br.com.fiap.lanchonete.produto.domain.entities.enums.CategoriaEnum
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.time.LocalDateTime


@Entity
@EntityListeners(AuditingEntityListener::class)
data class Produto(

        @SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq",
                allocationSize = 1)
        @GeneratedValue(generator = "produto_id_seq", strategy = GenerationType.SEQUENCE)
        @Id
        val id: Long? = null,
        var nome: String = "",
        @Column(nullable = false)
        var categoria: CategoriaEnum,
        var descricao: String = "",
        @Column(nullable = false)
        var preco: BigDecimal,
        @ElementCollection
        @CollectionTable(name = "imagens_produto")
        @Column(name = "string", length = 10485760)
        var imagens: List<String> = emptyList(),
        @CreatedDate
        @Column(name = "created_date", nullable = false, updatable = false)
        var createDate: LocalDateTime? = null,
        @LastModifiedDate
        @Column(name = "updated_date", nullable = false, updatable = false)
        var updateDate: LocalDateTime? = null
)