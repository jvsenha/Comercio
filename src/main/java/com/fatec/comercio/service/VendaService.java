package com.fatec.comercio.service;

import com.fatec.comercio.entity.VendaEntity;
import com.fatec.comercio.repository.VendaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;


    // Listar todos os registros
    @Transactional(readOnly = true)
    public List<VendaEntity> listar() {
        return vendaRepository.findAll();
    }

    // Salvar novo registro
    public VendaEntity save(VendaEntity venda) {
        try {
            return vendaRepository.saveAndFlush(venda);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados da venda!");
        }
    }

    // Alterar registro existente
    public VendaEntity alterar(long id, VendaEntity venda) {
        try {
            VendaEntity existente = buscarVenda(id);


            existente.setCliente(venda.getCliente());
            existente.setDataVenda(venda.getDataVenda());



            return vendaRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados do venda!");
        }
    }

    // Buscar registro pelo ID
    public VendaEntity buscarVenda(long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        VendaEntity existente = buscarVenda(id);
        vendaRepository.delete(existente);
    }
}
