package com.fatec.comercio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "venda_produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VendaProduto {

    @EmbeddedId
    private VendaProdutoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendaId")
    @JoinColumn(name = "venda_id", nullable = false)
    private VendaEntity venda;

    @Column(name = "quantidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;
}
