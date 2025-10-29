package com.fatec.comercio.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoId implements Serializable {
    private Long produtoId;
    private Long vendaId;
}
