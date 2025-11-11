package com.fatec.comercio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List; // <-- MUDANÇA (de Set para List)

@Entity
@Table(name = "Venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "dataVenda", nullable = false)
    private LocalDate dataVenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",  nullable = false)
    private ClienteEntity cliente;

    // --- BLOCO MODIFICADO ---
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) // <-- MUDANÇA (deve ser LAZY)
    @JsonIgnoreProperties("venda")
    private List<VendaProduto> itens; // <-- MUDANÇA (de Set para List)
}