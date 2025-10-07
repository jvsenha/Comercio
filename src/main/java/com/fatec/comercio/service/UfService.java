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


    // Listar todos os registros
    public List<UfEntity> listar() {
        return ufRepository.findAll();
    }

    // Salvar novo registro
    public UfEntity save(UfEntity uf) {
        if (uf.getName() == null || uf.getName().isBlank()) {
            throw new RuntimeException("Nome do uf não pode ser vazio");
        }
        return ufRepository.saveAndFlush(uf);
    }

    // Alterar registro existente
    public UfEntity alterar(long id, UfEntity uf) {
        UfEntity existente = buscarUf(id);

        existente.setName(uf.getName());
        existente.setSiglaUf(uf.getSiglaUf());
        return ufRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public UfEntity buscarUf(long id) {
        return ufRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uf não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        UfEntity existente = buscarUf(id);
        ufRepository.delete(existente);
    }
}
