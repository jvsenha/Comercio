package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.BairroEntity;
import com.fatec.comercio.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping("/list")
    public ResponseEntity<List<BairroEntity>> listar() {
        List<BairroEntity> lista = bairroService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            bairroService.deletar(id);
            resposta.put("mensagem", "O bairro com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar bairro com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody BairroEntity bairro) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            BairroEntity salvo = bairroService.save(bairro);
            resposta.put("mensagem", "Bairro salvo com sucesso");
            resposta.put("bairro", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar bairro");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody BairroEntity bairro) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            BairroEntity bairroEntityNovo = bairroService.alterar(id, bairro);
            resposta.put("mensagem", "Bairro alterado com sucesso");
            resposta.put("bairro", bairroEntityNovo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar bairro");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
