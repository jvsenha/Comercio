package com.fatec.comercio.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nomeCliente", nullable = false)
    private String name;

    @Column(name = "numeroCasa", nullable = false, length = 10)
    private String numeroCasa;

    @Column(name = "dataNasc", nullable = false)
    private LocalDate dataNasc;

    @Column(name = "cpfCliente", nullable=false , length = 11)
    private String cpfCliente;

    @ManyToOne
    @JoinColumn(name = "cidade_id",  nullable = false)
    private CidadeEntity cidade;

    @ManyToOne
    @JoinColumn(name = "rua_id",  nullable = false)
    private RuaEntity rua;

    @ManyToOne
    @JoinColumn(name = "bairro_id",  nullable = false)
    private BairroEntity bairro;

    @ManyToOne
    @JoinColumn(name = "cep_id",  nullable = false)
    private CepEntity cep;

    @ManyToOne
    @JoinColumn(name = "sexo_id", nullable = false)
    private SexoEntity sexo;


}

