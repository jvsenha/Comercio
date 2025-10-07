package com.fatec.comercio.service;

import com.fatec.comercio.entity.CidadeEntity;
import com.fatec.comercio.repository.CidadeRepository;
import com.fatec.comercio.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;


    // Listar todos os registros
    public List<CidadeEntity> listar() {
        return cidadeRepository.findAll();
    }

    // Salvar novo registro
    public CidadeEntity save(CidadeEntity cidade) {
        try {
            return cidadeRepository.saveAndFlush(cidade);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados da cidade!");
        }
    }

    // Alterar registro existente
    public CidadeEntity alterar(long id, CidadeEntity cidade) {
        try {
            CidadeEntity existente = buscarCidade(id);
            existente.setName(cidade.getName());
            existente.setUf(cidade.getUf());
            return cidadeRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados da cidade!");
        }
    }

    // Buscar registro pelo ID
    public CidadeEntity buscarCidade(long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        CidadeEntity existente = buscarCidade(id);
        cidadeRepository.delete(existente);
    }
}
