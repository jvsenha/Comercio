package com.fatec.comercio.service;

import com.fatec.comercio.entity.UfEntity;
import com.fatec.comercio.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UfService {

    @Autowired
    private UfRepository ufRepository;


    public List<UfEntity> listar() {
        return ufRepository.findAll();
    }

    public UfEntity save(UfEntity uf) {
        if (uf.getName() == null || uf.getName().isBlank()) {
            throw new RuntimeException("Nome do UF não pode ser vazio");
        }
        if (uf.getSiglaUf() == null || uf.getSiglaUf().isBlank()) {
            throw new RuntimeException("Sigla do UF não pode ser vazia");
        }
        return ufRepository.saveAndFlush(uf);
    }

    public UfEntity alterar(long id, UfEntity uf) {
        UfEntity existente = buscarUf(id);

        if (uf.getName() == null || uf.getName().isBlank()) {
            throw new RuntimeException("Nome do UF não pode ser vazio");
        }
        if (uf.getSiglaUf() == null || uf.getSiglaUf().isBlank()) {
            throw new RuntimeException("Sigla do UF não pode ser vazia");
        }

        existente.setName(uf.getName());
        existente.setSiglaUf(uf.getSiglaUf());
        return ufRepository.saveAndFlush(existente);
    }

    public UfEntity buscarUf(long id) {
        return ufRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uf não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        UfEntity existente = buscarUf(id);
        ufRepository.delete(existente);
    }
}