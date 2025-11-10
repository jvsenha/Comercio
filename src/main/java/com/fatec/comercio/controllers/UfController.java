package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.UfEntity;
import com.fatec.comercio.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/uf")
@CrossOrigin("*")
public class UfController {

    @Autowired
    private UfService ufService;

    @GetMapping("/list")
    public ResponseEntity<List<UfEntity>> listar() {
        List<UfEntity> lista = ufService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            ufService.deletar(id);
            resposta.put("mensagem", "O uf com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar uf com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody UfEntity ufNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            UfEntity salvo = ufService.save(ufNovo);
            resposta.put("mensagem", "Uf salvo com sucesso");
            resposta.put("uf", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar uf");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody UfEntity ufNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            UfEntity uf = ufService.alterar(id, ufNovo);
            resposta.put("mensagem", "Uf alterado com sucesso");
            resposta.put("uf", uf);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar uf");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
