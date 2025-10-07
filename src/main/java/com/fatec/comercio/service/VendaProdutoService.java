package com.fatec.comercio.service;

import com.fatec.comercio.entity.VendaProduto;
import com.fatec.comercio.repository.VendaProdutoRepository;
import com.fatec.comercio.repository.VendaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaProdutoService {

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;


    // Listar todos os registros
    public List<VendaProduto> listar() {
        return vendaProdutoRepository.findAll();
    }

    // Salvar novo registro
    public VendaProduto save(VendaProduto vendaProduto) {
        try {
            return vendaProdutoRepository.saveAndFlush(vendaProduto);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados da venda!");
        }
    }

    // Alterar registro existente
    public VendaProduto alterar(long id, VendaProduto vendaProduto) {
        try {
            VendaProduto existente = buscarVenda(id);


            existente.setProduto(vendaProduto.getProduto());
            existente.setVenda(vendaProduto.getVenda());
            existente.setQuantidade(vendaProduto.getQuantidade());
            existente.setPrecoUnitario(vendaProduto.getPrecoUnitario());



            return vendaProdutoRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados do vendaProduto!");
        }
    }

    // Buscar registro pelo ID
    public VendaProduto buscarVenda(long id) {
        return vendaProdutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("vendaProduto não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        VendaProduto existente = buscarVenda(id);
        vendaProdutoRepository.delete(existente);
    }
}
