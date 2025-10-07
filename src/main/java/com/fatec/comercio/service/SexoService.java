package com.fatec.comercio.service;

import com.fatec.comercio.entity.SexoEntity;
import com.fatec.comercio.repository.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexoService {

    @Autowired
    private SexoRepository sexoRepository;

    // Listar todos os registros
    public List<SexoEntity> listar() {
        return sexoRepository.findAll();
    }

    // Salvar novo registro
    public SexoEntity save(SexoEntity sexoEntity) {
        if (sexoEntity.getName() == null || sexoEntity.getName().isBlank()) {
            throw new RuntimeException("Nome do sexo não pode ser vazio");
        }
        return sexoRepository.saveAndFlush(sexoEntity);
    }

    // Alterar registro existente
    public SexoEntity alterar(long id, SexoEntity sexoEntity) {
        SexoEntity existente = buscarSexo(id);

        if (sexoEntity.getName() == null || sexoEntity.getName().isBlank()) {
            throw new RuntimeException("Nome do sexo não pode ser vazio");
        }

        existente.setName(sexoEntity.getName());
        return sexoRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public SexoEntity buscarSexo(long id) {
        return sexoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sexo não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        SexoEntity existente = buscarSexo(id);
        sexoRepository.delete(existente);
    }
}
