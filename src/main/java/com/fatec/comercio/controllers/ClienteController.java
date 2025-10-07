package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.ClienteEntity;
import com.fatec.comercio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/list")
    public ResponseEntity<List<ClienteEntity>> listar() {
        List<ClienteEntity> lista = clienteService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            clienteService.deletar(id);
            resposta.put("mensagem", "O cliente com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar cliente com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody ClienteEntity cliente) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            ClienteEntity salvo = clienteService.save(cliente);
            resposta.put("mensagem", "Cliente salvo com sucesso");
            resposta.put("cliente", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar cliente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            ClienteEntity clienteEntityNovo = clienteService.alterar(id, cliente);
            resposta.put("mensagem", "Cliente alterado com sucesso");
            resposta.put("cliente", clienteEntityNovo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar cliente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
