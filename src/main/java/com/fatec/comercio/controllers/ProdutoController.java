package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.ProdutoEntity;
import com.fatec.comercio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoEntity>> listar() {
        List<ProdutoEntity> lista = produtoService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            produtoService.deletar(id);
            resposta.put("mensagem", "O produto com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar produto com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody ProdutoEntity produtoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            ProdutoEntity salvo = produtoService.save(produtoNovo);
            resposta.put("mensagem", "Produto salvo com sucesso");
            resposta.put("produto", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar produto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody ProdutoEntity produtoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            ProdutoEntity produto = produtoService.alterar(id, produtoNovo);
            resposta.put("mensagem", "Produto alterado com sucesso");
            resposta.put("produto", produto);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar produto");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
