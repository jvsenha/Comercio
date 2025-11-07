package com.fatec.comercio.service;

import com.fatec.comercio.entity.ClienteEntity;
import com.fatec.comercio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private void validarCliente(ClienteEntity cliente) {
        if (cliente.getName() == null || cliente.getName().isBlank()) {
            throw new RuntimeException("O nome do cliente não pode ser vazio.");
        }
        if (cliente.getNumeroCasa() == null || cliente.getNumeroCasa().isBlank()) {
            throw new RuntimeException("O número da casa não pode ser vazio.");
        }
        if (cliente.getDataNasc() == null) {
            throw new RuntimeException("A data de nascimento não pode ser nula.");
        }
        if (cliente.getCpfCliente() == null || cliente.getCpfCliente().isBlank()) {
            throw new RuntimeException("O CPF do cliente não pode ser vazio.");
        }

        if (cliente.getCidade() == null || cliente.getCidade().getId() == null) {
            throw new RuntimeException("A Cidade é obrigatória.");
        }
        if (cliente.getRua() == null || cliente.getRua().getId() == null) {
            throw new RuntimeException("A Rua é obrigatória.");
        }
        if (cliente.getBairro() == null || cliente.getBairro().getId() == null) {
            throw new RuntimeException("O Bairro é obrigatório.");
        }
        if (cliente.getCep() == null || cliente.getCep().getId() == null) {
            throw new RuntimeException("O CEP é obrigatório.");
        }
        if (cliente.getSexo() == null || cliente.getSexo().getId() == null) {
            throw new RuntimeException("O Sexo é obrigatório.");
        }
    }


    public List<ClienteEntity> listar() {
        return clienteRepository.findAll();
    }

    public ClienteEntity save(ClienteEntity cliente) {
        try {
            validarCliente(cliente);
            return clienteRepository.saveAndFlush(cliente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public ClienteEntity alterar(long id, ClienteEntity cliente) {
        try {
            ClienteEntity existente = buscarCliente(id);
            validarCliente(cliente);


            existente.setName(cliente.getName());
            existente.setNumeroCasa(cliente.getNumeroCasa());
            existente.setDataNasc(cliente.getDataNasc());
            existente.setCpfCliente(cliente.getCpfCliente());
            existente.setCidade(cliente.getCidade());
            existente.setRua(cliente.getRua());
            existente.setBairro(cliente.getBairro());
            existente.setCep(cliente.getCep());
            existente.setSexo(cliente.getSexo());


            return clienteRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao alterar cliente: " + e.getMessage());
        }
    }

    public ClienteEntity buscarCliente(long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        ClienteEntity existente = buscarCliente(id);
        clienteRepository.delete(existente);
    }
}