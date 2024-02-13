package com.example.testniveaujava.controllers;

import com.example.testniveaujava.entites.Client;
import com.example.testniveaujava.reposirories.ClientRepository;
import com.example.testniveaujava.responses.MessageResponse;
import com.example.testniveaujava.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/clients" )
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }

    @GetMapping(value = "/{id}")
    public Client getClientByID(@PathVariable("id") int id) {
        return this.clientService.getClientByID(id);
    }

    @PostMapping
    public Client addNewClient(@RequestBody Client client) {
        return this.clientService.addNewClient(client);
    }

    @PutMapping(value = "/{id}")
    public Client updateClient(@PathVariable("id") int id, @RequestBody Client client) {
        return clientService.updateClientByID(id, client);
    }

    @DeleteMapping(value = "/{id}")
    public MessageResponse deleteClient(@PathVariable("id") int id) {
        return this.clientService.deleteClientById(id);
    }



}
