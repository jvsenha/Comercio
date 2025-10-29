package com.fatec.comercio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nomeProduto", nullable = false)
    private String name;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "quantidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidade;


    @ManyToOne
    @JoinColumn(name = "marca_id",  nullable = false)
    private MarcaEntity marca;

    @ManyToOne
    @JoinColumn(name = "tipo_id",  nullable = false)
    private TipoEntity tipo;

}
