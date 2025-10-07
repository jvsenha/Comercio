package com.fatec.comercio.service;

import com.fatec.comercio.entity.TipoEntity;
import com.fatec.comercio.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;


    // Listar todos os registros
    public List<TipoEntity> listar() {
        return tipoRepository.findAll();
    }

    // Salvar novo registro
    public TipoEntity save(TipoEntity tipo) {
        if (tipo.getName() == null || tipo.getName().isBlank()) {
            throw new RuntimeException("Nome do tipo não pode ser vazio");
        }
        return tipoRepository.saveAndFlush(tipo);
    }

    // Alterar registro existente
    public TipoEntity alterar(long id, TipoEntity tipo) {
        TipoEntity existente = buscarTipo(id);

        if (tipo.getName() == null || tipo.getName().isBlank()) {
            throw new RuntimeException("Nome do tipo não pode ser vazio");
        }

        existente.setName(tipo.getName());
        return tipoRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public TipoEntity buscarTipo(long id) {
        return tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        TipoEntity existente = buscarTipo(id);
        tipoRepository.delete(existente);
    }
}
