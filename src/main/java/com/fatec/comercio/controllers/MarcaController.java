package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.MarcaEntity;
import com.fatec.comercio.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/list")
    public ResponseEntity<List<MarcaEntity>> listar() {
        List<MarcaEntity> lista = marcaService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            marcaService.deletar(id);
            resposta.put("mensagem", "O marca com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar marca com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody MarcaEntity marca) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            MarcaEntity salvo = marcaService.save(marca);
            resposta.put("mensagem", "Marca salvo com sucesso");
            resposta.put("cep", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar marca");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody MarcaEntity marca) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            MarcaEntity marcaEntityNovo = marcaService.alterar(id, marca);
            resposta.put("mensagem", "Marca alterado com sucesso");
            resposta.put("cep", marcaEntityNovo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar marca");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
