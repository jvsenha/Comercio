package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.CidadeEntity;
import com.fatec.comercio.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/list")
    public ResponseEntity<List<CidadeEntity>> listar() {
        List<CidadeEntity> lista = cidadeService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            cidadeService.deletar(id);
            resposta.put("mensagem", "O cidade com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar cidade com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody CidadeEntity cidade) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            CidadeEntity salvo = cidadeService.save(cidade);
            resposta.put("mensagem", "Cidade salvo com sucesso");
            resposta.put("Cidade", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar cidade");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody CidadeEntity cidade) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            CidadeEntity cidadeEntityNovo = cidadeService.alterar(id, cidade);
            resposta.put("mensagem", "Cidade alterado com sucesso");
            resposta.put("cep", cidadeEntityNovo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar cidade");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
