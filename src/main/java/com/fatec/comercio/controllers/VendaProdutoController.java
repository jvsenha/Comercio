package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.VendaProduto;
import com.fatec.comercio.entity.VendaProdutoId;
import com.fatec.comercio.service.VendaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/vendaProduto")
@CrossOrigin("*")
public class VendaProdutoController {

    @Autowired
    private VendaProdutoService vendaProdutoService;

    @GetMapping("/list")
    public ResponseEntity<List<VendaProduto>> listar() {
        List<VendaProduto> lista = vendaProdutoService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{produtoId}/{vendaId}")
    public ResponseEntity<HashMap<String, Object>> deletaId(
            @PathVariable Long produtoId,
            @PathVariable Long vendaId) {

        VendaProdutoId id = new VendaProdutoId(produtoId, vendaId);
        HashMap<String, Object> resposta = new HashMap<>();

        try {
            vendaProdutoService.deletar(id);
            resposta.put("mensagem", "O vendaProduto com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar vendaProduto com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody VendaProduto vendaProdutoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            VendaProduto salvo = vendaProdutoService.salvar(vendaProdutoNovo);
            resposta.put("mensagem", "VendaProduto salvo com sucesso");
            resposta.put("vendaProduto", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar vendaProduto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{produtoId}/{vendaId}")
    public ResponseEntity<HashMap<String, Object>> alterar(
            @PathVariable Long produtoId,
            @PathVariable Long vendaId,
            @RequestBody VendaProduto vendaProdutoNovo) {

        VendaProdutoId id = new VendaProdutoId(produtoId, vendaId);
        HashMap<String, Object> resposta = new HashMap<>();

        try {
            VendaProduto vendaProduto = vendaProdutoService.alterar(id, vendaProdutoNovo);
            resposta.put("mensagem", "VendaProduto alterado com sucesso");
            resposta.put("vendaProduto", vendaProduto);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar vendaProduto");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
