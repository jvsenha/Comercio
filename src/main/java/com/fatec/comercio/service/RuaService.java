package com.fatec.comercio.service;

import com.fatec.comercio.entity.RuaEntity;
import com.fatec.comercio.repository.RuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuaService {

    @Autowired
    private RuaRepository ruaRepository;


    // Listar todos os registros
    public List<RuaEntity> listar() {
        return ruaRepository.findAll();
    }

    // Salvar novo registro
    public RuaEntity save(RuaEntity rua) {
        if (rua.getName() == null || rua.getName().isBlank()) {
            throw new RuntimeException("Nome do rua não pode ser vazio");
        }
        return ruaRepository.saveAndFlush(rua);
    }

    // Alterar registro existente
    public RuaEntity alterar(long id, RuaEntity rua) {
        RuaEntity existente = buscarRua(id);

        if (rua.getName() == null || rua.getName().isBlank()) {
            throw new RuntimeException("Nome do rua não pode ser vazio");
        }

        existente.setName(rua.getName());
        return ruaRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public RuaEntity buscarRua(long id) {
        return ruaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rua não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        RuaEntity existente = buscarRua(id);
        ruaRepository.delete(existente);
    }
}
