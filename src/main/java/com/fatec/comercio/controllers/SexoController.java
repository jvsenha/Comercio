package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.SexoEntity;
import com.fatec.comercio.service.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sexo")
@CrossOrigin("*")
public class SexoController {

    @Autowired
    private SexoService sexoService;

    @GetMapping("/list")
    public ResponseEntity<List<SexoEntity>> listar() {
        List<SexoEntity> lista = sexoService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            sexoService.deletar(id);
            resposta.put("mensagem", "O sexo com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar sexo com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody SexoEntity sexoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            SexoEntity salvo = sexoService.save(sexoNovo);
            resposta.put("mensagem", "Sexo salvo com sucesso");
            resposta.put("sexo", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar sexo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody SexoEntity sexoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            SexoEntity sexo = sexoService.alterar(id, sexoNovo);
            resposta.put("mensagem", "Sexo alterado com sucesso");
            resposta.put("sexo", sexo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar sexo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
