package com.fatec.comercio.service;

import com.fatec.comercio.entity.BairroEntity;
import com.fatec.comercio.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;


    // Listar todos os registros
    public List<BairroEntity> listar() {
        return bairroRepository.findAll();
    }

    // Salvar novo registro
    public BairroEntity save(BairroEntity bairro) {
        if (bairro.getName() == null || bairro.getName().isBlank()) {
            throw new RuntimeException("Nome do bairro não pode ser vazio");
        }
        return bairroRepository.saveAndFlush(bairro);
    }

    // Alterar registro existente
    public BairroEntity alterar(long id, BairroEntity bairro) {
        BairroEntity existente = buscarBairro(id);

        if (bairro.getName() == null || bairro.getName().isBlank()) {
            throw new RuntimeException("Nome do bairro não pode ser vazio");
        }

        existente.setName(bairro.getName());
        return bairroRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public BairroEntity buscarBairro(long id) {
        return bairroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bairro não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        BairroEntity existente = buscarBairro(id);
        bairroRepository.delete(existente);
    }
}
