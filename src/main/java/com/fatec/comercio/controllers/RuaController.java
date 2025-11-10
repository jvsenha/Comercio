package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.RuaEntity;
import com.fatec.comercio.service.RuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/rua")
@CrossOrigin("*")
public class RuaController {

    @Autowired
    private RuaService ruaService;

    @GetMapping("/list")
    public ResponseEntity<List<RuaEntity>> listar() {
        List<RuaEntity> lista = ruaService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            ruaService.deletar(id);
            resposta.put("mensagem", "O rua com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar rua com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody RuaEntity ruaNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            RuaEntity salvo = ruaService.save(ruaNovo);
            resposta.put("mensagem", "Rua salvo com sucesso");
            resposta.put("cep", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar rua");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody RuaEntity ruaNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            RuaEntity rua = ruaService.alterar(id, ruaNovo);
            resposta.put("mensagem", "Rua alterado com sucesso");
            resposta.put("cep", rua);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar rua");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
