package com.fatec.comercio.repository;

import com.fatec.comercio.entity.VendaProduto;
import com.fatec.comercio.entity.VendaProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto,VendaProdutoId> {
}
