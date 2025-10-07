package com.fatec.comercio.service;

import com.fatec.comercio.entity.MarcaEntity;
import com.fatec.comercio.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;


    // Listar todos os registros
    public List<MarcaEntity> listar() {
        return marcaRepository.findAll();
    }

    // Salvar novo registro
    public MarcaEntity save(MarcaEntity marca) {
        if (marca.getName() == null || marca.getName().isBlank()) {
            throw new RuntimeException("Nome do marca não pode ser vazio");
        }
        return marcaRepository.saveAndFlush(marca);
    }

    // Alterar registro existente
    public MarcaEntity alterar(long id, MarcaEntity marca) {
        MarcaEntity existente = buscarMarca(id);

        if (marca.getName() == null || marca.getName().isBlank()) {
            throw new RuntimeException("Nome do marca não pode ser vazio");
        }

        existente.setName(marca.getName());
        return marcaRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public MarcaEntity buscarMarca(long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        MarcaEntity existente = buscarMarca(id);
        marcaRepository.delete(existente);
    }
}
