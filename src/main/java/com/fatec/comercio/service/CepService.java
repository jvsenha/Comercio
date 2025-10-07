package com.fatec.comercio.service;

import com.fatec.comercio.entity.CepEntity;
import com.fatec.comercio.repository.CepRepository;
import com.fatec.comercio.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;


    // Listar todos os registros
    public List<CepEntity> listar() {
        return cepRepository.findAll();
    }

    // Salvar novo registro
    public CepEntity save(CepEntity cep) {
        if (cep.getNumeroCep() == null || cep.getNumeroCep().isBlank()) {
            throw new RuntimeException("Numero do cep não pode ser vazio");
        }
        return cepRepository.saveAndFlush(cep);
    }

    // Alterar registro existente
    public CepEntity alterar(long id, CepEntity cep) {
        CepEntity existente = buscarCep(id);

        if (cep.getNumeroCep() == null || cep.getNumeroCep().isBlank()) {
            throw new RuntimeException("Numero do cep não pode ser vazio");
        }

        existente.setNumeroCep(cep.getNumeroCep());
        return cepRepository.saveAndFlush(existente);
    }

    // Buscar registro pelo ID
    public CepEntity buscarCep(long id) {
        return cepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cep não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        CepEntity existente = buscarCep(id);
        cepRepository.delete(existente);
    }
}
