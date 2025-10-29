package com.fatec.comercio.service;

import com.fatec.comercio.entity.ProdutoEntity;
import com.fatec.comercio.entity.VendaEntity;
import com.fatec.comercio.entity.VendaProduto;
import com.fatec.comercio.entity.VendaProdutoId;
import com.fatec.comercio.repository.ProdutoRepository;
import com.fatec.comercio.repository.VendaProdutoRepository;
import com.fatec.comercio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaProdutoService {

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;



    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public List<VendaProduto> listar() {
        return vendaProdutoRepository.findAll();
    }


    public VendaProduto salvar(VendaProduto vendaProduto) {
        try {
            Long produtoId = vendaProduto.getId().getProdutoId();
            Long vendaId = vendaProduto.getId().getVendaId();

            ProdutoEntity produto = produtoRepository.findById(produtoId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            VendaEntity venda = vendaRepository.findById(vendaId)
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

            vendaProduto.setProduto(produto);
            vendaProduto.setVenda(venda);

            return vendaProdutoRepository.saveAndFlush(vendaProduto);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar vendaProduto: " + e.getMessage());
        }
    }

    public VendaProduto alterar(VendaProdutoId id, VendaProduto vendaProduto) {
        try {
            VendaProduto existente = buscarPorId(id);
            existente.setProduto(vendaProduto.getProduto());
            existente.setVenda(vendaProduto.getVenda());
            existente.setQuantidade(vendaProduto.getQuantidade());
            existente.setPrecoUnitario(vendaProduto.getPrecoUnitario());
            return vendaProdutoRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao alterar vendaProduto: " + e.getMessage());
        }
    }

    public VendaProduto buscarPorId(VendaProdutoId id) {
        return vendaProdutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "VendaProduto não encontrado com produtoId=" + id.getProdutoId() +
                                " e vendaId=" + id.getVendaId()));
    }

    public void deletar(VendaProdutoId id) {
        VendaProduto existente = buscarPorId(id);
        vendaProdutoRepository.delete(existente);
    }
}
