package com.fatec.comercio.controllers;

import com.fatec.comercio.entity.CepEntity;
import com.fatec.comercio.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cep")
@CrossOrigin("*")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/list")
    public ResponseEntity<List<CepEntity>> listar() {
        List<CepEntity> lista = cepService.listar();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deletaId(@PathVariable Long id) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            cepService.deletar(id);
            resposta.put("mensagem", "O cep com id: " + id + " foi apagado com sucesso");
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao deletar cep com id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> save(@RequestBody CepEntity cep) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            CepEntity salvo = cepService.save(cep);
            resposta.put("mensagem", "Cep salvo com sucesso");
            resposta.put("cep", salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Erro ao salvar cep");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> alterar(@PathVariable Long id, @RequestBody CepEntity cep) {
        HashMap<String, Object> resposta = new HashMap<>();
        try {
            CepEntity cepEntityNovo = cepService.alterar(id, cep);
            resposta.put("mensagem", "Cep alterado com sucesso");
            resposta.put("cep", cepEntityNovo);
            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            resposta.put("mensagem", "Problemas ao alterar cep");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}
