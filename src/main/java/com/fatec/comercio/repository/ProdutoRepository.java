package com.fatec.comercio.repository;

import com.fatec.comercio.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
}
