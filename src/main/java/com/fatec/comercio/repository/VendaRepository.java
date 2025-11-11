package com.fatec.comercio.repository;

import com.fatec.comercio.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<VendaEntity,Long> {
    @Query("SELECT DISTINCT v FROM VendaEntity v LEFT JOIN FETCH v.itens")
    List<VendaEntity> findAll();
}
