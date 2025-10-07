package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.TipoEntity;
import com.fatec.comercio.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping("/list")
    public ResponseEntity<List<TipoEntity>> listar() {
        List<TipoEntity> lista = tipoService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            tipoService.deletar(id);
            resposta.put("mensagem", "O tipo com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar tipo com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody TipoEntity tipoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            TipoEntity tipo = tipoService.save(tipoNovo);
            resposta.put("mensagem", "Tipo salvo com sucesso");
            resposta.put("tipo", tipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar tipo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody TipoEntity tipoNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            TipoEntity tipo = tipoService.alterar(id, tipoNovo);
            resposta.put("mensagem", "Tipo alterado com sucesso");
            resposta.put("tipo", tipo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar tipo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
