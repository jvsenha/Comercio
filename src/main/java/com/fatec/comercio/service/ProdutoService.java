package com.fatec.comercio.service;

import com.fatec.comercio.entity.ProdutoEntity;
import com.fatec.comercio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    // Listar todos os registros
    public List<ProdutoEntity> listar() {
        return produtoRepository.findAll();
    }

    // Salvar novo registro
    public ProdutoEntity save(ProdutoEntity produto) {
        try {
            return produtoRepository.saveAndFlush(produto);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados da produto!");
        }
    }

    // Alterar registro existente
    public ProdutoEntity alterar(long id, ProdutoEntity produto) {
        try {
            ProdutoEntity existente = buscarProduto(id);


            existente.setName(produto.getName());
            existente.setTipo(produto.getTipo());
            existente.setMarca(produto.getMarca());
            existente.setValor(produto.getValor());
            existente.setQuantidade(produto.getQuantidade());


            return produtoRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados do produto!");
        }
    }

    // Buscar registro pelo ID
    public ProdutoEntity buscarProduto(long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        ProdutoEntity existente = buscarProduto(id);
        produtoRepository.delete(existente);
    }
}
