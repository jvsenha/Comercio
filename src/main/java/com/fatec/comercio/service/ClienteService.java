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


    // Listar todos os registros
    public List<ClienteEntity> listar() {
        return clienteRepository.findAll();
    }

    // Salvar novo registro
    public ClienteEntity save(ClienteEntity cliente) {
        try {
            return clienteRepository.saveAndFlush(cliente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados da cliente!");
        }
    }

    // Alterar registro existente
    public ClienteEntity alterar(long id, ClienteEntity cliente) {
        try {
            ClienteEntity existente = buscarCliente(id);


            existente.setName(cliente.getName());
            existente.setNumeroCasa(cliente.getNumeroCasa());
            existente.setDataNasc(cliente.getDataNasc());
            existente.setCpfCliente(cliente.getCpfCliente());
            existente.setCidade(cliente.getCidade());
            existente.setRua(cliente.getRua());
            existente.setBairro(cliente.getBairro());
            existente.setCep(cliente.getCep());


            return clienteRepository.saveAndFlush(existente);
        } catch (RuntimeException e) {
            throw new RuntimeException("Verifique os dados do cliente!");
        }
    }

    // Buscar registro pelo ID
    public ClienteEntity buscarCliente(long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    // Deletar registro pelo ID
    public void deletar(Long id) {
        ClienteEntity existente = buscarCliente(id);
        clienteRepository.delete(existente);
    }
}
