package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.VendaEntity;
import com.fatec.comercio.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/venda")
@CrossOrigin("*")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/list")
    public ResponseEntity<List<VendaEntity>> listar() {
        List<VendaEntity> lista = vendaService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            vendaService.deletar(id);
            resposta.put("mensagem", "O venda com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar venda com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody VendaEntity vendaNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            VendaEntity salvo = vendaService.save(vendaNovo);
            resposta.put("mensagem", "Venda salvo com sucesso");
            resposta.put("venda", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar venda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody VendaEntity vendaNovo) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            VendaEntity venda = vendaService.alterar(id, vendaNovo);
            resposta.put("mensagem", "Venda alterado com sucesso");
            resposta.put("venda", venda);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar venda");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
