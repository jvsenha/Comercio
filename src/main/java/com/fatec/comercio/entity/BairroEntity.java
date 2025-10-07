package com.fatec.comercio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bairro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BairroEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     @Column(name = "nomeBairro", nullable = false, length = 100)
     private String name;
}
